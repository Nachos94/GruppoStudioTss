/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Userbusiness;

import com.mycompany.Filebusiness.FileCloud;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author andrea.antonazzo
 */
@NamedQueries({
    @NamedQuery(name = User.FIND_ALL, query = "select u from User u")
    ,
  @NamedQuery(name = User.FIND_BY_EMAIL, query = "select u from User u where u.email = :email")
    ,
  @NamedQuery(name = User.FIND_BY_USERNAME, query = "select u from User u where u.Username = :username")
    ,
  @NamedQuery(name = User.FIND_BY_ID, query = "select u from User u where u.id = :id")
    ,
  @NamedQuery(name = User.FIND_BY_TOKEN , query = "select u from User u where u.token = :token")

})

@Entity
public class User implements Serializable {

    public static final String FIND_ALL = "User.findAll";
    public static final String FIND_BY_ID = "User.findById";
    public static final String FIND_BY_USERNAME = "User.findByUsername";
    public static final String FIND_BY_EMAIL = "User.findByEmail";
    public static final String FIND_BY_TOKEN = "User.findByToken";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;
    private Date tokenend;

    public Date getTokenend() {
        return tokenend;
    }

    public void setTokenend(Date tokenend) {
        this.tokenend = tokenend;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    private String Username;
    private String Nome;
    private String Cognome;
    private String email;
    private String password;
    private List<FileCloud> filesAssociati;

    public User() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getCognome() {
        return Cognome;
    }

    public void setCognome(String Cognome) {
        this.Cognome = Cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public  List<FileCloud> getFilesAssociati() {
        return filesAssociati;
    }

    public void setFilesAssociati( List<FileCloud> filesAssociati) {
        this.filesAssociati = filesAssociati;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.id);
        hash = 17 * hash + Objects.hashCode(this.Username);
        hash = 17 * hash + Objects.hashCode(this.email);
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
        final User other = (User) obj;
        if (!Objects.equals(this.Username, other.Username)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
