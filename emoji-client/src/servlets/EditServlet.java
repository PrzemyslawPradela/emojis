package servlets;

import com.google.gson.Gson;
import ws.client.Emoji;
import ws.client.EmojiWebApi;
import ws.client.EmojiWebApiService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EditServlet", urlPatterns = "/emoji")
public class EditServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);

        EmojiWebApi service = new EmojiWebApiService().getEmojiWebApiPort();
        Emoji emoji = service.findById(id);

        String json = new Gson().toJson(emoji);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
