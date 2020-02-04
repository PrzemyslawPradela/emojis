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
import java.util.List;

@WebServlet(name = "IndexServlet", urlPatterns = "/index")
public class IndexServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        EmojiWebApi service = new EmojiWebApiService().getEmojiWebApiPort();
        List<Emoji> emojis = service.getEmojis();

        String json = new Gson().toJson(emojis);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
