package emojis.controller.servlets;

import emojis.ws.client.Emoji;
import emojis.ws.client.EmojiService;
import emojis.ws.client.EmojiWebService;
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

@WebServlet(name = "AddEmojiServlet", urlPatterns = {"/AddEmojiServlet"})
@MultipartConfig
public class AddEmojiServlet extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/emojis-ws/EmojiService.wsdl")
    private EmojiService service;

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
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Part filePart = request.getPart("icon");
        InputStream fileContent = filePart.getInputStream();
        byte[] icon = IOUtils.toByteArray(fileContent);
        Emoji emoji = new Emoji();
        emoji.setName(name);
        emoji.setDescription(description);
        emoji.setIcon(icon);
        port.addEmoji(emoji);
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
