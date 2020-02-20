package emojis.client.servlet;

import com.google.gson.Gson;
import emojis.client.Emoji;
import emojis.client.EmojisService;
import emojis.client.EmojisService_Service;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;

@WebServlet(name = "IndexServlet", urlPatterns = {"/IndexServlet"})
public class IndexServlet extends HttpServlet {

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
            List<Emoji> result = port.getEmojis();
            String json = new Gson().toJson(result);
            response.setContentType("aplication/json;charset=UTF-8");
            response.getWriter().write(json);
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
