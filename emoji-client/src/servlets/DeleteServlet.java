package servlets;

import ws.client.EmojiWebApi;
import ws.client.EmojiWebApiService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteServlet", urlPatterns = "/delete")
public class DeleteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);

        EmojiWebApi service = new EmojiWebApiService().getEmojiWebApiPort();
        service.deleteById(id);

        response.sendRedirect("");
    }
}
