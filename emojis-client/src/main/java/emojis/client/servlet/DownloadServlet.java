package emojis.client.servlet;

import emojis.client.Emoji;
import emojis.client.EmojiService;
import emojis.client.EmojiService_Service;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.Normalizer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;

@WebServlet(name = "DownloadServlet", urlPatterns = {"/DownloadServlet"})
public class DownloadServlet extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/services/EmojiService.wsdl")
    private EmojiService_Service service;

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
            EmojiService port = service.getEmojiServicePort();
            String emojiIdString = request.getParameter("id");
            int emojiId = Integer.parseInt(emojiIdString);
            Emoji result = port.getEmojiById(emojiId);
            byte[] icon = result.getIcon();
            String emojiName = result.getEmojiName();
            String fileName = Normalizer.normalize(emojiName
                    .replaceAll(" ", "_")
                    .toLowerCase()
                    .concat(".png"), Normalizer.Form.NFD)
                    .replaceAll("[^\\p{ASCII}]", "");
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
        } catch (Exception ex) {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println(ex.getMessage());
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
    }// </editor-fold>

}
