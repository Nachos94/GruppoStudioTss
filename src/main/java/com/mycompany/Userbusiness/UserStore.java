/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Userbusiness;

import com.mycompany.utility.TokenExpiredException;
import com.mycompany.utility.UserNotFoundException;
import java.util.List;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.time.temporal.ChronoUnit;
import javax.persistence.PersistenceContext;

/**
 *
 * @author andrea.antonazzo
 */
@Stateless
public class UserStore {

    @PersistenceContext
    EntityManager em;

    public List<User> findAll() {

        return em.createNamedQuery(User.FIND_ALL)
                .getResultList();
    }

    public User findById(long id) {

        return em.createNamedQuery(User.FIND_BY_ID, User.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public User findByUsername(String Username) {

        return em.createNamedQuery(User.FIND_BY_USERNAME, User.class)
                .setParameter("Username", Username)
                .getSingleResult();
    }

    public User findByEmail(String email) {

        return em.createNamedQuery(User.FIND_BY_EMAIL, User.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    public void addUser(User u) {
        em.merge(u);

    }

    public User login(String username, String password) throws Exception {

        User u = null;

        if (findByUsername(username) != null && password.equals(findByUsername(username).getPassword())) {
            u = findByUsername(username);
            u.setToken(UUID.randomUUID().toString());
            LocalDateTime tend = LocalDateTime.now().plus(60, ChronoUnit.MINUTES);
            Date tokenend = Date.from(tend.atZone(ZoneId.systemDefault()).toInstant());
            u.setTokenend(tokenend);

        } else {

            throw new UserNotFoundException();
        }

        return u;
    }

    public User findToken(String token) {

        return em.createNamedQuery(User.FIND_BY_TOKEN, User.class)
                .setParameter("token", token)
                .getSingleResult();
    }

    public void validaUser(User user) throws Exception {

        if (findToken(user.getToken()) != null
                && user.getTokenend().before(new Date())) {
        } else {

            throw new TokenExpiredException();
        }

    }

}
