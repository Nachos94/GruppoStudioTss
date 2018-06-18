/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author andrea.antonazzo
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:~/h2/db", "prova", "prova");
        PreparedStatement ps = conn.prepareStatement("INSERT INTO PIPPO VALUES (1)");
        ps.executeUpdate();
        ps.close();
        conn.close();
    }
    
}
