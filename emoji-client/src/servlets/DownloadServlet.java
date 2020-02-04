package servlets;

import ws.client.EmojiWebApi;
import ws.client.EmojiWebApiService;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@WebServlet(name = "DownloadServlet", urlPatterns = "/download")
public class DownloadServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);

        EmojiWebApi service = new EmojiWebApiService().getEmojiWebApiPort();
        byte[] icon = service.findById(id).getIcon();
        String fileName = service.findById(id).getName().replaceAll(" ", "_").toLowerCase().concat(".png");
        fileName.replaceAll("[^\\p{ASCII}]", "");

        InputStream inputStream = new ByteArrayInputStream(icon);
        int fileLength = inputStream.available();
        ServletContext context = getServletContext();
        String mimeType = context.getMimeType(fileName);
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }

        response.setContentType(mimeType);
        response.setContentLength(fileLength);
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", fileName);
        response.setHeader(headerKey, headerValue);

        OutputStream outStream = response.getOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead = -1;

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
        inputStream.close();
        outStream.close();
    }
}
