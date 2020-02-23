package emojis.web.servlets;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

@WebServlet(name = "UpdateServlet", urlPatterns = {"/UpdateServlet"})
@MultipartConfig
public class UpdateServlet extends HttpServlet {

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
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String id = request.getParameter("id");
        HttpGet httpGet = new HttpGet("http://localhost:8080/emojis-controller/UpdateEmojiServlet?id=" + id);
        try (CloseableHttpResponse httpResponse = httpClient.execute(httpGet)) {
            HttpEntity entity = httpResponse.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                response.setCharacterEncoding("utf-8");
                response.getWriter().println(result);
            }
        } finally {
            httpClient.close();
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            String id = request.getParameter("id");
            HttpPost httpPost = new HttpPost("http://localhost:8080/emojis-controller/UpdateEmojiServlet?id=" + id);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            request.setCharacterEncoding("UTF-8");
            String name = request.getParameter("name");
            if (!name.isEmpty()) {
                builder.addTextBody("name", name, ContentType.TEXT_PLAIN.withCharset("UTF-8"));
            }
            String description = request.getParameter("description");
            if (!description.isEmpty()) {
                builder.addTextBody("description", description, ContentType.TEXT_PLAIN.withCharset("UTF-8"));
            }
            Part icon = request.getPart("icon");
            if (icon != null) {
                InputStream fileContent = icon.getInputStream();
                byte[] bytes = IOUtils.toByteArray(fileContent);
                builder.addBinaryBody("icon", bytes, ContentType.DEFAULT_BINARY, name);
            }
            HttpEntity entity = builder.build();
            httpPost.setEntity(entity);
            httpClient.execute(httpPost);
        }
        response.sendRedirect("index.html");
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
