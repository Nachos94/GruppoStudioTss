/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.todoweb.business.users;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
        User user = userstore.login(username, password);
         JsonObject json = null;
        if(user == null)  {
        
             System.out.println("elemento assente");
             Json.createObjectBuilder()
                        .add("errore" , "elemento assente")
                        .build();
        } else {
        
        System.out.println(user);
        json = Json.createObjectBuilder()
                        .add("token", user.getToken())
                        .build();
            System.out.println(user + " " + user.getToken() + " " + user.getTokenend());
        
        }
        
        return Response.ok(json).build();
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
