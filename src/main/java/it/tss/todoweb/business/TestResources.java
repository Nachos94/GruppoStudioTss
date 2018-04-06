/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.todoweb.business;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author tss
 */
@Path("test")
public class TestResources {

    @GET
    public String msg() {
        return "ciao";
    }

    @GET
    @Path("json")
    public JsonObject obj() {

        return Json.createObjectBuilder()
                .add("id", 1)
                .add("titolo", "json")
                .add("testo", "testo json")
                .build();

    }

    @sicurityFilter
    @GET
    @Path("resp")
    public Response resp() {
        return Response.ok("ciao").build();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") String id) {
        System.out.println("update ..." + id);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") String id) {
        System.out.println("delete ..." + id);
        return Response.ok().build();
    }

    @POST
    @Path("form")
    public Response form(@FormParam("usr") String usr, @FormParam("pwd") String pwd) {
        System.out.println(String.format("usr:%s, pwd:%s", usr, pwd));
        return Response.ok().build();
    }

    @POST
    @Path("json")
    public Response json(JsonObject o) {
        System.out.println(String.format("JSON usr:%s, pwd:%s",
                o.getString("usr"), o.getString("pwd")));
        return Response.ok().build();
    }

    @POST
    @Path("todo")
    public Response todo(ToDo o) {
        System.out.println(String.format("Todo: %s", o.toString()));
        return Response.ok().build();
    }
    
    
    
    
}