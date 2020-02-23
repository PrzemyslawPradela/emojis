package emojis.web.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

@WebServlet(name = "DownloadServlet", urlPatterns = {"/DownloadServlet"})
public class DownloadServlet extends HttpServlet {

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
        String id = request.getParameter("id");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGetEmojiName = new HttpGet("http://localhost:8080/emojis-controller/GetEmojiNameServlet?id=" + id);
        String emojiName = null;
        try (CloseableHttpResponse httpResponse = httpClient.execute(httpGetEmojiName)) {
            HttpEntity entity = httpResponse.getEntity();
            if (entity != null) {
                emojiName = EntityUtils.toString(entity);
            }
        }

        HttpGet httpGetEmojiIcon = new HttpGet("http://localhost:8080/emojis-controller/DownloadIconServlet?id=" + id);
        try (CloseableHttpResponse httpResponse = httpClient.execute(httpGetEmojiIcon)) {
            HttpEntity entity = httpResponse.getEntity();
            if (entity != null) {
                response.setContentType("image/png");
                response.setHeader("Content-disposition", "attachment; filename=" + emojiName);
                InputStream inputStream = entity.getContent();
                try (OutputStream outputStream = response.getOutputStream()) {
                    IOUtils.copy(inputStream, outputStream);
                }
            }
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
