/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mqtttest;
import java.sql.*;
import java.util.*;
/**
 *
 * @author almis
 */
public class Maria_Select {
    
    static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    static final String DB_URL = "jdbc:mariadb://localhost:3306/Vet";
    static final String USER = "root";
    static final String PASS = "12345";

   
    public List selectX(double id){
        Connection conn = null;
        Statement stmt = null;
        List xValues = new ArrayList();
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/Vet", "root", "12345");
           
            stmt = conn.createStatement();
            ResultSet x = stmt.executeQuery("select x from messages where id = " + id );

      while (x.next())
      {
        xValues.add(x.getDouble(1));
      }
      System.out.println(xValues);
      
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
        return xValues;
    
    }
    public List selectY(double id){
        Connection conn = null;
        Statement stmt = null;
        List yValues = new ArrayList();
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/Vet", "root", "12345");
           
            stmt = conn.createStatement();
            ResultSet y = stmt.executeQuery("select y from messages where id = " + id );

      while (y.next())
      {
        yValues.add(y.getDouble(1));
      }
      System.out.println(yValues);
      
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
        return yValues;
    
    }
    
    public List selectZ(double id){
        Connection conn = null;
        Statement stmt = null;
        List zValues = new ArrayList();
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/Vet", "root", "12345");
           
            stmt = conn.createStatement();
            ResultSet z = stmt.executeQuery("select z from messages where id = " + id );

      while (z.next())
      {
        zValues.add(z.getDouble(1));
      }
      System.out.println(zValues);
      
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
        return zValues;
    
    }
    
}
