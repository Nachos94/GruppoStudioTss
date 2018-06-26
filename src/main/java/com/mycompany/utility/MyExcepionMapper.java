/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.utility;

import java.io.FileNotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 *
 * @author andrea.antonazzo
 */
public class MyExcepionMapper implements ExceptionMapper<Exception> {

    private final String ALREADY_HAVE_THAT_USER = "L' USER INSERITO E' GIA' PRESENTE , VERIFICARE EMAIL O USERNAME";
    private final String USER_NOT_FOUND = "Utente non trovato, procedi alla registrazione o controlla i tuoi dati d'accesso";
    private final String TOKEN_EXPIRED = "SESSIONE SCAUTA O UTENTE NON ABILITATO , RIPETERE L'ACCESSO";
    private final String DUPLICATE_FILE = "IL FILE CHE SI STA CERCANDO DI INSERIRE ESISTE GIA' , SCEGLIERE UN ALTRO FILE O RINOMINARLO";
    private final String FILE_NOT_FOUND ="File non trovato, procedi all'upload o controlla i dati inviati";
    
    
    @Override
    public Response toResponse(Exception exception) {
        Response response = null;

        if (exception instanceof AlreadyHaveThatUserException) {
            response = Response.status(Response.Status.BAD_REQUEST)
                    .header("causa", ALREADY_HAVE_THAT_USER)
                    .build();
        }
        if (exception instanceof UserNotFoundException) {

            response = Response.status(Response.Status.BAD_REQUEST)
                    .header("causa", USER_NOT_FOUND)
                    .build();

        }
        if (exception instanceof TokenExpiredException) {

            response = Response.status(Response.Status.UNAUTHORIZED)
                    .header("causa", TOKEN_EXPIRED)
                    .build();
        }
        if (exception instanceof DuplicateFileCloudException) {

            response = Response.status(Response.Status.BAD_REQUEST)
                    .header("causa", DUPLICATE_FILE)
                    .build();
        }

        if (exception instanceof FileNotFoundException) {
            
             response = Response.status(Response.Status.NOT_FOUND)
                    .header("causa", FILE_NOT_FOUND)
                    .build();
            
        }
        
        if(exception instanceof NullPointerException) {
            
            response = Response.status(Response.Status.BAD_REQUEST)
                    .header("causa", "errore generico")
                    .build();
            
        }
        
        return response;
    }

}
