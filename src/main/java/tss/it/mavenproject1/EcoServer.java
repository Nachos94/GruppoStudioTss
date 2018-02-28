/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tss.it.mavenproject1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author tss
 */
public class EcoServer {
static String comandoRx;
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    // fare in modo che il server risponda al client facendo in continuazione l'eco dell'imput dato fino alla scrittura della parola "fine" 
    public static void main(String[] args) throws IOException {
        System.out.println("Server started");
        //InetAddress addr=InetAddress.getByName("192.168.40.212);   //192.168.40.213
        ServerSocket srvSocket = new ServerSocket(21000);
         System.out.println("Waiting connection...");
       
         do { 
           
            Socket s = srvSocket.accept(); // spinotto collegamneto con client 
            System.out.println("Connection accepted...");
 
            InputStream is = s.getInputStream();
            BufferedReader bf = new BufferedReader(new InputStreamReader(is));
            comandoRx = bf.readLine();
            System.out.println(comandoRx);

            
            OutputStream outputStream = s.getOutputStream();
            PrintWriter w = new PrintWriter(outputStream, true);
            
            w.println(comandoRx +"   "+ comandoRx );

            if(comandoRx.equals("fine")) {

                s.close();
                System.out.println("connection ended...");}
            

 }while(true); 
 
 
 
 
    }

}
