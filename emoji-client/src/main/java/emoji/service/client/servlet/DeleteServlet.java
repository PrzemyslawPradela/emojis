package emoji.service.client.servlet;

import emoji.service.client.EmojiWebService;
import emoji.service.client.EmojiWebService_Service;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;

@WebServlet(name = "DeleteServlet", urlPatterns = {"/DeleteServlet"})
public class DeleteServlet extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/emojis/api/EmojiWebService.wsdl")
    private EmojiWebService_Service service;

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
            String emojString = request.getParameter("id");
            EmojiWebService port = service.getEmojiWebServicePort();
            int emojiId = Integer.parseInt(emojString);
            port.removeEmojiById(emojiId);
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
