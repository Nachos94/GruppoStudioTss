/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Filebusiness;

import com.mycompany.Userbusiness.User;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

/**
 *
 * @author andrea.antonazzo
 */
@NamedQueries({
    @NamedQuery(name = FileCloud.FIND_ALL_BY_USER, query = "select f from FileCloud f where f.user.Username = :username")
    ,
@NamedQuery(name = FileCloud.FIND_BY_DATE, query = "select f.identificativo from FileCloud f where f.data = :data")
    ,
@NamedQuery(name = FileCloud.FIND_BY_IDENTIFICATIVO, query = "select f.identificativo from FileCloud f where f.identificativo = :identificativo AND f.user.Username = :username")
    ,
@NamedQuery(name = FileCloud.FIND_BY_ID, query = "select f.identificativo from FileCloud f where f.id = :id")

})

@Entity
public class FileCloud implements Serializable {

    public static final String FIND_BY_ID = "FileCloud.findById";
    public static final String FIND_ALL_BY_USER = "FileCloud.findAllByUser";
    public static final String FIND_BY_IDENTIFICATIVO = "FileCloud.findByIdentificativo";
    public static final String FIND_BY_DATE = "FileCloud.findByDate";

    public FileCloud() {

        this.data = new Date();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private InputStream file;

    private User user;
    private String identificativo;
    private Date data;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + Objects.hashCode(this.identificativo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FileCloud other = (FileCloud) obj;
        if (!Objects.equals(this.identificativo, other.identificativo)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public InputStream getFile() {

        return file;
    }

    public void setFile(InputStream inputstream) throws IOException {

        this.file = file;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getIdentificativo() {
        return identificativo;
    }

    public void setIdentificativo(String identificativo) {
        this.identificativo = identificativo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
