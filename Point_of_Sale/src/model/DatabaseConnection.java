/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ACER
 */
public class DatabaseConnection {
    
    public    Connection    getConnection() throws SQLException{
          Connection con=null;
            String dbname="mydb";
            String username="root";
            String password="admin";
        try {
          
          
             Class.forName("com.mysql.jdbc.Driver");
            System.out.println("load Driver...");
            con=DriverManager.getConnection("jdbc:mysql://localhost/"+dbname, username, password);
            System.out.println("Database connected....");
                
        } catch (ClassNotFoundException ex) {
            System.out.println("database is not connected");
        }
      return con;
    }
    
}
