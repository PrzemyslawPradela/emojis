package emoji.service.api.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "EMOJI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Emoji.findAll", query = "SELECT e FROM Emoji e"),
    @NamedQuery(name = "Emoji.findByEmojiId", query = "SELECT e FROM Emoji e WHERE e.emojiId = :emojiId"),
    @NamedQuery(name = "Emoji.findByEmojiName", query = "SELECT e FROM Emoji e WHERE e.emojiName = :emojiName"),
    @NamedQuery(name = "Emoji.findByDescription", query = "SELECT e FROM Emoji e WHERE e.description = :description")})
public class Emoji implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "EMOJI_ID")
    private Integer emojiId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "EMOJI_NAME")
    private String emojiName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "ICON")
    private byte[] icon;
    @Transient
    private String base64Icon;

    public Emoji() {
    }

    public Emoji(Integer emojiId) {
        this.emojiId = emojiId;
    }

    public Emoji(Integer emojiId, String emojiName, String description, byte[] icon) {
        this.emojiId = emojiId;
        this.emojiName = emojiName;
        this.description = description;
        this.icon = icon;
    }

    public Integer getEmojiId() {
        return emojiId;
    }

    public void setEmojiId(Integer emojiId) {
        this.emojiId = emojiId;
    }

    public String getEmojiName() {
        return emojiName;
    }

    public void setEmojiName(String emojiName) {
        this.emojiName = emojiName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getIcon() {
        return icon;
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
    }

    public String getBase64Icon() {
        return base64Icon;
    }

    public void setBase64Icon(String base64Icon) {
        this.base64Icon = base64Icon;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (emojiId != null ? emojiId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Emoji)) {
            return false;
        }
        Emoji other = (Emoji) object;
        if ((this.emojiId == null && other.emojiId != null) || (this.emojiId != null && !this.emojiId.equals(other.emojiId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "emoji.service.api.entities.Emoji[ emojiId=" + emojiId + " ]";
    }

}
