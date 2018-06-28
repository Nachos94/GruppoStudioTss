/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Filebusiness;

import com.mycompany.utility.DuplicateFileCloudException;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author andrea.antonazzo
 */
@Stateless
public class FileCloudStore {

    private final String DATA_DIR = "/home/tss/Documenti/";

    public String getDataDir() {

        return this.DATA_DIR;
    }

    @PersistenceContext
    EntityManager em;

    public List<FileCloud> findAllbyUser(String username) {

        return em.createNamedQuery(FileCloud.FIND_ALL_BY_USER)
                .setParameter("username", username)
                .getResultList();
    }

    public FileCloud findByID(long id) {

        return em.createNamedQuery(FileCloud.FIND_BY_ID, FileCloud.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public FileCloud findByIdentificativo(String identificativo, String username) {

        try {
            return em.createNamedQuery(FileCloud.FIND_BY_IDENTIFICATIVO, FileCloud.class)
                    .setParameter("identificativo", identificativo)
                    .setParameter("username", username)
                    .getSingleResult();

        } catch (Exception ex) {

            return null;

        }
    }

    public List<FileCloud> findByData(Date data) {

        return em.createNamedQuery(FileCloud.FIND_BY_DATE, FileCloud.class)
                .setParameter("data", data)
                .getResultList();
    }

    public void insert(FileCloud filecloud) throws Exception {

        if (filecloud.getId() == null && findByIdentificativo(filecloud.getIdentificativo(), filecloud.getUser().getUsername()) == null) {
            //fare outputstream di scrittura su disco del server per salvare il file contenuto in filecloud 
            //ma che non viene trattato come colonna grazie Juri

            em.merge(filecloud);
            File file = new File(DATA_DIR + "/Datadir/" + filecloud.getIdentificativo());
           
            FileOutputStream out = new FileOutputStream(file);
           
            out.write(filecloud.getFile());
           
            out.close();
                      

        } else {

            throw new DuplicateFileCloudException();

        }
    }

    public void delete(long id) {

        FileCloud f = findByID(id);
        File file = new File(DATA_DIR + f.getIdentificativo());

        if (file != null) {
            file.delete();
        }

        em.remove(f);

    }

}
