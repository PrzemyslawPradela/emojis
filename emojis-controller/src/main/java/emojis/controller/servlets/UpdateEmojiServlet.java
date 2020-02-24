package emojis.controller.servlets;

import com.google.gson.Gson;
import emojis.ws.client.Emoji;
import emojis.ws.client.EmojiService;
import emojis.ws.client.EmojiWebService;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.ws.WebServiceRef;
import org.apache.commons.io.IOUtils;

@WebServlet(name = "UpdateEmojiServlet", urlPatterns = {"/UpdateEmojiServlet"})
@MultipartConfig
public class UpdateEmojiServlet extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/emojis-ws/EmojiService.wsdl")
    private EmojiService service;

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
        EmojiWebService port = service.getEmojiWebServicePort();
        String idString = request.getParameter("id");
        int id = Integer.parseInt(idString);
        Emoji emoji = port.getEmojiById(id);
        String json = new Gson().toJson(emoji);
        response.setContentType("aplication/json;charset=UTF-8");
        response.getWriter().write(json);
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
        EmojiWebService port = service.getEmojiWebServicePort();
        request.setCharacterEncoding("UTF-8");
        String idString = request.getParameter("id");
        int id = Integer.parseInt(idString);
        Emoji emoji = port.getEmojiById(id);
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Part filePart = request.getPart("icon");
        if (!name.isEmpty() && !name.equals(emoji.getName())) {
            emoji.setName(name);
            port.updateEmoji(emoji);
        }
        if (!description.isEmpty() && !description.equals(emoji.getDescription())) {
            emoji.setDescription(description);
            port.updateEmoji(emoji);
        }
        if (filePart != null) {
            InputStream fileContent = filePart.getInputStream();
            byte[] icon = IOUtils.toByteArray(fileContent);
            if (!Arrays.equals(icon, emoji.getIcon())) {
                emoji.setIcon(icon);
                port.updateEmoji(emoji);
            }
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
