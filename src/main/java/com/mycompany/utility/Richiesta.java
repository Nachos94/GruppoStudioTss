/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.utility;

import com.mycompany.Filebusiness.FileCloud;
import com.mycompany.Userbusiness.User;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tss
 */
@XmlRootElement
public class Richiesta {

    private FileCloud filecloud;
    private User user;
    private String username;
    private String password;
    private long id;
    

    public Richiesta() {
    
}
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
            
    public FileCloud getFilecloud() {
        return filecloud;
    }

    public void setFilecloud(FileCloud filecloud) {
        this.filecloud = filecloud;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
