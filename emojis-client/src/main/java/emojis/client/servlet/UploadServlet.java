package emojis.client.servlet;

import emojis.client.Emoji;
import emojis.client.EmojiService;
import emojis.client.EmojiService_Service;
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

@WebServlet(name = "UploadServlet", urlPatterns = {"/UploadServlet"})
@MultipartConfig
public class UploadServlet extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/services/EmojiService.wsdl")
    private EmojiService_Service service;

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
            EmojiService port = service.getEmojiServicePort();
            request.setCharacterEncoding("UTF-8");
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            Part filePart = request.getPart("icon");
            InputStream fileContent = filePart.getInputStream();
            byte[] icon = IOUtils.toByteArray(fileContent);
            Emoji emoji = new Emoji();
            emoji.setEmojiName(name);
            emoji.setDescription(description);
            emoji.setIcon(icon);
            port.addEmoji(emoji);
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
    }// </editor-fold>

}
