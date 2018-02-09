/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.management.Query;
import javax.sql.DataSource;

/**
 *
 * @author tss
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        
      
       
        DataSource ds = DsFactory.getMySQLDataSource() ;
         
        try(Connection con = ds.getConnection();) { 
            
        System.out.println("Connessione DB ok" ); 
        Statement cmd = con.createStatement();
        
        ResultSet q1 = cmd.executeQuery("select * from classicmodels.customers"); 
                
        
        while (q1.next()){
                               
       String nome_cliente =  q1.getString("customerName");
       String numero_telefono =  q1.getString("phone");
       
       
            System.out.println( nome_cliente + " " + numero_telefono );
            
        }
                
                
        q1.close();
        cmd.close();
        con.close();
        
        
            }catch (SQLException ex){
                
                System.out.println("Ops...errore!" + ex.getMessage());
            }
     
}
        
        }