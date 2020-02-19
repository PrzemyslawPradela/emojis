package emoji.service.api;

import emoji.service.api.entities.Emoji;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;

@WebService(serviceName = "EmojiWebService")
public class EmojiWebService {

    /**
     * Web service operation
     *
     * @param emoji
     */
    @WebMethod(operationName = "addEmoji")
    @Oneway
    public void addEmoji(@WebParam(name = "emoji") Emoji emoji) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("emojiPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(emoji);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    /**
     * Web service operation
     *
     * @param emoji
     */
    @WebMethod(operationName = "updateEmoji")
    @Oneway
    public void updateEmoji(@WebParam(name = "emoji") Emoji emoji) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("emojiPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.merge(emoji);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    /**
     * Web service operation
     *
     * @param emojiId
     */
    @WebMethod(operationName = "removeEmojiById")
    @Oneway
    public void removeEmojiById(@WebParam(name = "emojiId") int emojiId) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("emojiPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Emoji emoji = entityManager.find(Emoji.class, emojiId);
        if (emoji != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(emoji);
            entityManager.getTransaction().commit();
        }

        entityManager.close();
        entityManagerFactory.close();
    }

    /**
     * Web service operation
     *
     * @param emojiId
     * @return emoji
     */
    @WebMethod(operationName = "getEmojiById")
    public Emoji getEmojiById(@WebParam(name = "emojiId") int emojiId) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("emojiPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Emoji emoji = entityManager.find(Emoji.class, emojiId);
        if (emoji == null) {
            throw new EntityNotFoundException("Nie można znaleźć Emoji z ID " + emojiId);
        }

        entityManager.close();
        entityManagerFactory.close();
        byte[] imageBytes = emoji.getIcon();
        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
        emoji.setBase64Icon(base64Image);
        return emoji;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getEmojis")
    public List<Emoji> getEmojis() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("emojiPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Query query = entityManager.createQuery("select e from Emoji e", Emoji.class);
        List<Emoji> emojis = query.getResultList();
        List<Emoji> emojisWithBase64Images = new ArrayList<>();
        for (Emoji emoji
                : emojis) {
            byte[] imageBytes = emoji.getIcon();
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
            emoji.setBase64Icon(base64Image);
            emojisWithBase64Images.add(emoji);
        }

        entityManager.close();
        entityManagerFactory.close();
        return emojisWithBase64Images;
    }
}
