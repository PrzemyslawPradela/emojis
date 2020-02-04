package dao;

import entities.Emoji;

import java.util.List;

public interface EmojiDao {
    Emoji findById(int id);

    void save(Emoji emoji);

    void update(Emoji emoji);

    List<Emoji> getEmojis();

    void deleteById(int id);
}
