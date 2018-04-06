/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.todoweb.business;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author tss
 */
@Stateless
public class ToDoStore {

    @PersistenceContext
    private EntityManager em;

    public List<ToDo> findAll() {
        return em.createNamedQuery(ToDo.FIND_ALL, ToDo.class)
                .getResultList();
    }

    public List<ToDo> findToday() {
        return em.createNamedQuery(ToDo.FIND_BY_DATE)
                .setParameter("p_data", new Date())
                .getResultList();
    }

    public List<ToDo> find(Date d) {
        return em.createNamedQuery(ToDo.FIND_BY_DATE)
                .setParameter("p_data", d)
                .getResultList();
    }

    public ToDo find(long id) {
        return em.find(ToDo.class, id);
    }

    public List<ToDo> find(String word) {
        return em.createNamedQuery(ToDo.FIND_BY_WORD, ToDo.class)
                .setParameter("word", "%" + word + "%")
                .getResultList();
    }

    public void save(ToDo tosave) {
        em.merge(tosave);
    }

    public void delete(long id) {
        ToDo finded = find(id);
        em.remove(finded);
    }

}
