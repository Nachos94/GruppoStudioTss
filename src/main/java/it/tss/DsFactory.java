/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import javax.sql.DataSource;



/**
 *
 * @author tss
 */
public class DsFactory {
    
    private static final String MYSQL_DB_DRIVER_CLASS = "com.mysql.jdbc.Driver";
    private static final String MYSQL_DB_URL= "jdbc:mysql://localhost:3306/classicmodels";
    private static final String MYSQL_DB_USERNAME = "root";
    private static final String MYSQL_DB_PASSWORD = "mysql";
    
    public static DataSource getMySQLDataSource() {
        
       MysqlDataSource mysqlDS = null;
       mysqlDS = new MysqlDataSource();
       mysqlDS.setURL(MYSQL_DB_URL);
       mysqlDS.setUser(MYSQL_DB_USERNAME);
       mysqlDS.setPassword(MYSQL_DB_PASSWORD);
       
       return mysqlDS; 
      
    }
    
}
