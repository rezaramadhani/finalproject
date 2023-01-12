/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject.finalprojectkamis.model.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Reza Ramadhani
 */
@Entity
@Table(name = "uas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Uas.findAll", query = "SELECT u FROM Uas u"),
    @NamedQuery(name = "Uas.findById", query = "SELECT u FROM Uas u WHERE u.id = :id"),
    @NamedQuery(name = "Uas.findByName", query = "SELECT u FROM Uas u WHERE u.name = :name"),
    @NamedQuery(name = "Uas.findByNik", query = "SELECT u FROM Uas u WHERE u.nik = :nik"),
    @NamedQuery(name = "Uas.findByAdress", query = "SELECT u FROM Uas u WHERE u.adress = :adress")})
public class Uas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "Name")
    private String name;
    @Column(name = "NIK")
    private String nik;
    @Column(name = "Adress")
    private String adress;
    @Lob
    @Column(name = "Photo")
    private byte[] photo;

    public Uas() {
    }

    public Uas(Integer id) {
        this.id = id;
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

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
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
        if (!(object instanceof Uas)) {
            return false;
        }
        Uas other = (Uas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "finalproject.finalprojectkamis.model.entity.Uas[ id=" + id + " ]";
    }
    
}
