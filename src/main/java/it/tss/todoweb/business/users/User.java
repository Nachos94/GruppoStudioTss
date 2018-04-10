/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.todoweb.business.users;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;


/**
 *
 * @author tss
 */

@NamedQueries ({

@NamedQuery(name=User.FIND_ALL ,query ="select u from User u order by u.nome desc "),
    @NamedQuery(name=User.FIND_EMAIL,query ="select u from User u where u.email = :email"),
    @NamedQuery(name=User.FIND_PASS ,query ="select u from User u where u.password = :pwd"),
    @NamedQuery(name= User.FIND_USERNAME, query="select u from User u where u.nome = :username"),
    @NamedQuery(name=User.FIND_TOKEN, query="select u from User u where u.token = :token")
})


@Entity
public class User implements Serializable {
    
    public static final String FIND_ALL = "User.findAll"; 
    public static final String FIND_EMAIL = "User.findEmail";
    public static final String FIND_PASS = "User.findPass";    
    public static final String FIND_USERNAME = "User.findUsername"; 
    public static final String FIND_TOKEN = "User.findToken";
    
    private String token;
    
    @Version
    private long version;
    
   @Temporal(TemporalType.TIMESTAMP)
    private Date tokenend;
    
    @Basic(optional = false)
    private String nome;
    @Basic(optional = false)
    private String password;
    @Basic(optional = false)
    private String email;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User() {
    }

    
    public User(String nome, String password, String email) {
        this.nome = nome;
        this.password = password;
        this.email = email;
    }
    
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "nome=" + nome + ", password=" + password + ", email=" + email + ", id=" + id + '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getTokenend() {
        return tokenend;
    }

    public void setTokenend(Date tokenend) {
        this.tokenend = tokenend;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    
 
    
}
