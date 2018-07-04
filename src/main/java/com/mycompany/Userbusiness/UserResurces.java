/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Userbusiness;

import com.mycompany.Filebusiness.FileCloud;
import com.mycompany.Filebusiness.FileCloudStore;
import com.mycompany.utility.AlreadyHaveThatUserException;
import com.mycompany.utility.DuplicateFileCloudException;
import com.mycompany.utility.Richiesta;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.ContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;


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
    @Path("/get")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response aggiornaprofilo(Richiesta richiesta) throws Exception{
        
       User user = userstore.findById(richiesta.getUser().getId());
       
        List<FileCloud> listafile = filecloudstore.findAllbyUser(user.getUsername());
        
        for(FileCloud f : listafile) {
            
            f.setUser(null);
            
        }
        
        user.setFilesAssociati(listafile);
        
        return Response.ok(user).build();
    }

    @POST
    @Path("/saveuser")
    @Consumes(MediaType.APPLICATION_JSON)
     @Produces(MediaType.APPLICATION_JSON)
    public Response saveUser(Richiesta richiesta) throws Exception {

        User u = richiesta.getUser();

        if (userstore.findByUsername(u.getUsername()) == null && userstore.findByEmail(u.getEmail()) == null) {
            userstore.addUser(u);
            return Response.ok().build();
        } else {
            throw new AlreadyHaveThatUserException();
        }
    }

    @POST
    @Path("/caricaprofilo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response caricaprofilo(Richiesta richiesta) throws Exception {

        User user = richiesta.getUser();
        User u = userstore.login(user.getUsername(), user.getPassword());

        List<FileCloud> listafile = filecloudstore.findAllbyUser(u.getUsername());
        u.setFilesAssociati(listafile);

        return Response.ok(u).build();
    }

    @POST
    @Path("/aggiungifile")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response aggiungiFile(FormDataMultiPart form) throws Exception {

         FileCloud filecloud = new FileCloud();
         filecloud.setIdentificativo(form.getField("identificativo").getValue());
         
       User user = userstore.findById(Long.parseLong(form.getField("userid").getValue()));
          
        if(filecloudstore.findByIdentificativo(filecloud.getIdentificativo(), user.getUsername()) == null){
            FormDataBodyPart filePart = form.getField("file");
            ContentDisposition contentDispositionHeader = filePart.getContentDisposition();

            InputStream fileInputStream = filePart.getValueAs(InputStream.class);

            Files.copy(fileInputStream,
                    Paths.get(filecloudstore.getDataDir() +"/Datadir/" + contentDispositionHeader.getFileName()),
                    StandardCopyOption.REPLACE_EXISTING);
      
        } else {
            
            
            throw new DuplicateFileCloudException();
        }
      
      
       filecloud.setUser(user); 
      filecloudstore.insert(filecloud);
      userstore.validaUser(user);
      user.getFilesAssociati().add(filecloud);
          
     
        
        return Response.ok().build();
    
    }
    
    

    @DELETE
    @Path("/rimuovifile")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response rimuoviFile(Richiesta richiesta) throws Exception {

        long id = richiesta.getId();
        User user = richiesta.getUser();

        userstore.validaUser(user);
        filecloudstore.delete(id);

        User u = userstore.findByUsername(user.getUsername());

        return Response.ok(u).build();
    }

    @POST
    @Path("/scaricafile")
    @Consumes(MediaType.APPLICATION_JSON)
    
    public Response scaricaFile(Richiesta richiesta) throws Exception {

        long fileid = richiesta.getId();

        FileCloud f = filecloudstore.findByID(fileid);
        File file = new File(filecloudstore.getDataDir() + f.getIdentificativo());

        if (file == null) {
            throw new FileNotFoundException();
        }

        return Response.ok(file).build();
    }

}
