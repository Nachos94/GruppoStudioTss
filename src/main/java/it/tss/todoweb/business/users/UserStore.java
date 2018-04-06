/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.todoweb.business.users;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author tss
 */
@Stateless
public class UserStore {

    @PersistenceContext
    private EntityManager em;

    public User findByUsername(String username) {
        return em.createNamedQuery(User.FIND_USERNAME, User.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    public List<User> findAll() {
        return em.createNamedQuery(User.FIND_ALL, User.class)
                .getResultList();
    }

    public User findEmail(String email) {
        return em.createNamedQuery(User.FIND_EMAIL, User.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    public User findPass(String pwd) {
        return em.createNamedQuery(User.FIND_PASS, User.class)
                .setParameter("pwd", pwd)
                .getSingleResult();
    }

    public void save(User u) {
        em.merge(u);
    }

    public User findById(long id) {

        return em.find(User.class, id);
    }

    public void delete(long id) {
        User u = findById(id);
        em.remove(u);
    }

    public boolean login(String username, String password) {

        boolean check = false;
      if (findByUsername(username) != null && findPass(password) != null) {
            check = true;
        }
        return check;
    }

}
