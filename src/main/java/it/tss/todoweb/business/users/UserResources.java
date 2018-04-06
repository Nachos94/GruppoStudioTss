/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.todoweb.business.users;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

/**
 *
 * @author tss
 */
@Path("user")
public class UserResources {

    @Inject
    UserStore userstore;

    @POST
    @Path("/autentica")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response find(User u) {
        
        String password = u.getPassword();
        String username = u.getNome();
       boolean check = userstore.login(username,password);
        System.out.println(check);
        return Response.ok().build();

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(User u) {
        System.out.println("save json...");
        System.out.println(u.toString());
        userstore.save(u);

        return Response.ok().build();

    }

}
