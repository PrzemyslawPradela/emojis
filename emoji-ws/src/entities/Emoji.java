package entities;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;

@Entity
public class Emoji {
    private int id;
    private String name;
    private String description;
    private byte[] icon;
    private String base64Image;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "ICON")
    public byte[] getIcon() {
        return icon;
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
    }

    @Transient
    public String getBase64Image() {
        base64Image = Base64.getEncoder().encodeToString(this.icon);
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emoji emoji = (Emoji) o;
        return id == emoji.id &&
                Objects.equals(name, emoji.name) &&
                Objects.equals(description, emoji.description) &&
                Arrays.equals(icon, emoji.icon);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, description);
        result = 31 * result + Arrays.hashCode(icon);
        return result;
    }
}
