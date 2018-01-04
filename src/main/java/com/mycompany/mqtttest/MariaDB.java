/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mqtttest;
import java.sql.*;
/**
 *
 * @author almis
 */
public class MariaDB {
    
    Subscriber sub;
    String string;
    
    static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    static final String DB_URL = "jdbc:mariadb://localhost:3306/Vet";
    static final String USER = "root";
    static final String PASS = "12345";

   
    public void insertDB(Double x, Double y, Double z, Double id){
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/Vet", "root", "12345");
            System.out.println("Connected database successfully...");

            System.out.println("Inserting Data...");
            stmt = conn.createStatement();

           String sql =  "insert into messages(x, y, z, id) values( " + x + " , " + y + " ," + z + "," + id + ");";
            
            stmt.executeUpdate(sql);
            System.out.println("Data inserted...");
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    
    }
    
    
}
