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
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author tss
 */


public class EcoClient {
static String tx;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        Socket s = new Socket("192.168.40.213", 21000); //192.168.40.213  "192.168.40.212"
    
      
       
        //creo un pacchetto di output
         OutputStream outputStream = s.getOutputStream();
         PrintWriter out = new PrintWriter(outputStream, true);
        //riempio il pacchetto di output
      do{
         tx = JOptionPane.showInputDialog("cosa vuoi scrivere al server?");
         out.println(tx);
         
          //creo pacchetto di input
         InputStream is = s.getInputStream(); 
         BufferedReader bf = new BufferedReader(new InputStreamReader(is));
         String comandoRx = bf.readLine();
        //stampo pacchetto di input    
         System.out.println(comandoRx);
         
         if(tx.equals("fine")) {
           s.close();
           System.out.println("connection ended..."); }
      
       }while(!tx.equals("fine"));
       
         
        
         
       
   
   
        
       
       
       /* InputStream is = s.getInputStream(); 
        BufferedReader bf = new BufferedReader(new InputStreamReader(is));
        String comandoRx = bf.readLine();
       System.out.println(comandoRx);
         */
        
    
    
}
    
}


