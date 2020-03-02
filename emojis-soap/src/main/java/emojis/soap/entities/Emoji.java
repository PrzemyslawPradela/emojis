package emojis.soap.entities;

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
    @NamedQuery(name = "Emoji.findById", query = "SELECT e FROM Emoji e WHERE e.id = :id"),
    @NamedQuery(name = "Emoji.findByName", query = "SELECT e FROM Emoji e WHERE e.name = :name"),
    @NamedQuery(name = "Emoji.findByDescription", query = "SELECT e FROM Emoji e WHERE e.description = :description")})
public class Emoji implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "NAME")
    private String name;
    @NotNull
    @Size(max = 1024)
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

    public Emoji(Integer id) {
        this.id = id;
    }

    public Emoji(Integer id, String name, byte[] icon) {
        this.id = id;
        this.name = name;
        this.icon = icon;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Emoji)) {
            return false;
        }
        Emoji other = (Emoji) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "emojis.ws.Emoji[ id=" + id + " ]";
    }

}
