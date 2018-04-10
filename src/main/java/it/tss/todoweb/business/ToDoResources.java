/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.todoweb.business;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author tss
 */

@Path("todo")
public class ToDoResources {

    @Inject
    ToDoStore store;
    
    @Secured
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ToDo> all() {
        return store.findAll();
    }

   /* @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ToDo find(@PathParam("id") Long id) {
        return store.find(id);
    }*/

    @GET
    @Path("search/today")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ToDo> find() {
        return store.find(new Date());
    }

    @GET
    @Path("find/{word}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ToDo> find(@PathParam("word") String word) {
        return store.find(word);
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response create(@FormParam("titolo") String titolo,
            @FormParam("testo") String testo, @FormParam("il") String il) throws ParseException {
        ToDo tosave;
        tosave = new ToDo(titolo, testo, DateUtils.dateFromString(il));
        store.save(tosave);
        return Response.ok().build();

    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response update(@PathParam("id") Long id, @FormParam("titolo") String titolo,
            @FormParam("testo") String testo, @FormParam("il") String il) throws ParseException {
        ToDo tosave;
        System.out.println(String.format("---UPDATE---- titolo:%s, testo=%s, il:%s", titolo, testo, il));

        tosave = store.find(id);
        tosave.setTitolo(titolo);
        tosave.setTesto(testo);
        tosave.setIl(DateUtils.dateFromString(il));
        store.save(tosave);
        return Response.ok().build();

    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        store.delete(id);
        return Response.ok().build();
    }

}
