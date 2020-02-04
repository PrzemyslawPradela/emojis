package ws.server;

import dao.EmojiDao;
import entities.Emoji;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.*;
import javax.xml.ws.Endpoint;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@WebService()
public class EmojiWebApi implements EmojiDao {
  public static void main(String[] argv) {
    Object implementor = new EmojiWebApi();
    String address = "http://localhost:9000/EmojiWebApi";
    Endpoint.publish(address, implementor);
  }

  @WebMethod
  @Override
  public Emoji findById(int id) {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("emoji");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    Emoji emoji = entityManager.find(Emoji.class, id);
    if (emoji == null) {
      throw new EntityNotFoundException("Nie można znaleźć Emoji z ID " + id);
    }

    entityManager.close();
    entityManagerFactory.close();
    byte[] imageBytes = emoji.getIcon();
    String base64Image = Base64.getEncoder().encodeToString(imageBytes);
    emoji.setBase64Image(base64Image);
    return emoji;
  }

  @WebMethod
  @Override
  public void save(Emoji emoji) {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("emoji");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    entityManager.getTransaction().begin();
    entityManager.persist(emoji);
    entityManager.getTransaction().commit();

    entityManager.close();
    entityManagerFactory.close();
  }

  @WebMethod
  @Override
  public void update(Emoji emoji) {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("emoji");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    entityManager.getTransaction().begin();
    entityManager.merge(emoji);
    entityManager.getTransaction().commit();

    entityManager.close();
    entityManagerFactory.close();
  }

  @WebMethod
  @Override
  public List<Emoji> getEmojis() {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("emoji");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    Query query = entityManager.createQuery("select e from Emoji e", Emoji.class);
    List<Emoji> emojis = query.getResultList();
    List<Emoji> emojisWithBase64Images = new ArrayList<>();
    for (Emoji emoji :
            emojis) {
      byte[] imageBytes = emoji.getIcon();
      String base64Image = Base64.getEncoder().encodeToString(imageBytes);
      emoji.setBase64Image(base64Image);
      emojisWithBase64Images.add(emoji);
    }

    entityManager.close();
    entityManagerFactory.close();
    return emojisWithBase64Images;
  }

  @WebMethod
  @Override
  public void deleteById(int id) {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("emoji");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    Emoji emoji = entityManager.find(Emoji.class, id);
    if (emoji != null) {
      entityManager.getTransaction().begin();
      entityManager.remove(emoji);
      entityManager.getTransaction().commit();
    }

    entityManager.close();
    entityManagerFactory.close();
  }
}
