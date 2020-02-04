package servlets;

import org.apache.commons.io.IOUtils;
import ws.client.Emoji;
import ws.client.EmojiWebApi;
import ws.client.EmojiWebApiService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(name = "UploadServlet", urlPatterns = "/upload")
public class UploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Part filePart = request.getPart("icon");
        InputStream fileContent = filePart.getInputStream();
        byte[] icon = IOUtils.toByteArray(fileContent);

        EmojiWebApi service = new EmojiWebApiService().getEmojiWebApiPort();
        Emoji emoji = new Emoji();
        emoji.setName(name);
        emoji.setDescription(description);
        emoji.setIcon(icon);
        service.save(emoji);

        response.sendRedirect("");
    }
}
