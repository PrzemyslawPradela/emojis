package emojis.client.servlet;

import com.google.gson.Gson;
import emojis.client.Emoji;
import emojis.client.EmojisService;
import emojis.client.EmojisService_Service;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.ws.WebServiceRef;
import org.apache.commons.io.IOUtils;

@WebServlet(name = "UpdateServlet", urlPatterns = {"/UpdateServlet"})
@MultipartConfig
public class UpdateServlet extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/services/EmojisService.wsdl")
    private EmojisService_Service service;

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try { // Call Web Service Operation
            EmojisService port = service.getEmojisServicePort();
            String emojiIdString = request.getParameter("id");
            int emojiId = Integer.parseInt(emojiIdString);
            Emoji emoji = port.getEmojiById(emojiId);
            String json = new Gson().toJson(emoji);
            response.setContentType("aplication/json;charset=UTF-8");
            response.getWriter().write(json);
        } catch (Exception ex) {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(ex.getMessage());
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try { // Call Web Service Operation
            EmojisService port = service.getEmojisServicePort();
            request.setCharacterEncoding("UTF-8");
            String emojiIdString = request.getParameter("id");
            int emojiId = Integer.parseInt(emojiIdString);
            Emoji emoji = port.getEmojiById(emojiId);
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            Part filePart = request.getPart("icon");
            if (!name.isEmpty() || !name.equals(emoji.getEmojiName())) {
                emoji.setEmojiName(name);
            }
            if (!description.isEmpty()) {
                emoji.setDescription(description);
            }
            if (filePart != null) {
                InputStream fileContent = filePart.getInputStream();
                byte[] icon = IOUtils.toByteArray(fileContent);
                emoji.setIcon(icon);
            }
            port.updateEmoji(emoji);
            response.sendRedirect("index.html");
        } catch (Exception ex) {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(ex.getMessage());
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
