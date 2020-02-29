package emojis.controller.servlets;

import com.google.gson.Gson;
import emojis.controller.soap.client.Emoji;
import emojis.controller.soap.client.EmojiService;
import emojis.controller.soap.client.EmojiWebService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;

@WebServlet(name = "GetEmojisServlet", urlPatterns = {"/GetEmojisServlet"})
public class GetEmojisServlet extends HttpServlet {

    @WebServiceRef(wsdlLocation = "http://localhost:8080/emojis-soap/EmojiService?wsdl")
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
        List<Emoji> result = port.getEmojis();
        String json = new Gson().toJson(result);
        response.setContentType("aplication/json;charset=UTF-8");
        response.getWriter().write(json);
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
