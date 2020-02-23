package emojis.controller.servlets;

import emojis.ws.client.Emoji;
import emojis.ws.client.EmojiService;
import emojis.ws.client.EmojiWebService;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.Normalizer;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;

@WebServlet(name = "GetEmojiNameServlet", urlPatterns = {"/GetEmojiNameServlet"})
public class GetEmojiNameServlet extends HttpServlet {

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
        Emoji result = port.getEmojiById(id);
        String name = result.getName();
        String emojiName = Normalizer.normalize(name
                .replaceAll(" ", "_")
                .toLowerCase()
                .concat(".png"), Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.write(emojiName);
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
