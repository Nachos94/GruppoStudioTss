/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Userbusiness;

import com.mycompany.Filebusiness.FileCloud;
import com.mycompany.Filebusiness.FileCloudStore;
import com.mycompany.utility.AlreadyHaveThatUserException;
import java.io.File;
import java.io.FileNotFoundException;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author andrea.antonazzo
 */
@Path("/userstore")
public class UserResurces {

    @Inject
    UserStore userstore;
    @Inject
    FileCloudStore filecloudstore;

    @POST
    @Path("/saveuser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveUser(User u) throws Exception {

        if (userstore.findByUsername(u.getUsername()) != null && userstore.findByEmail(u.getEmail()) != null) {
            userstore.addUser(u);
            return Response.ok().build();
        } else {
            throw new AlreadyHaveThatUserException();
        }

    }

    @POST
    @Path("/caricaprofilo")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response caricaprofilo(String username, String password) throws Exception {

        User u = userstore.login(username, password);
        List<FileCloud> listafile = filecloudstore.findAllbyUser(u.getUsername());
        u.setFilesAssociati(listafile);

        return Response.ok(u).build();
    }

    @POST
    @Path("/aggiungifile")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response aggiungiFile(FileCloud filecloud, User user) throws Exception {

        userstore.validaUser(user);
        filecloud.setUser(user);
        filecloudstore.insert(filecloud);

        User u = userstore.findByUsername(user.getUsername());

        return Response.ok(u).build();
    }

    @DELETE
    @Path("/rimuovifile")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response rimuoviFile(long id, User user) throws Exception {

        userstore.validaUser(user);
        filecloudstore.delete(id);

        User u = userstore.findByUsername(user.getUsername());

        return Response.ok(u).build();
    }

    @POST
    @Path("/scaricafile")
    public Response scaricaFile(long fileid) throws Exception {

        FileCloud f = filecloudstore.findByID(fileid);
        File file = new File(filecloudstore.getDataDir() + f.getIdentificativo());

        if (file == null) {
            throw new FileNotFoundException();
        }

        return Response.ok(file).build();
    }

}
