/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jdbc;

import java.sql.*;


/**
 *
 * @author ander
 */
public class ConnectionFactory {
    
    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost/tareasdb", "root", "wacaballa");
        } catch (SQLException ex) {
            System.out.println("Error de conexión: " + ex.getMessage());
            return null;
        }
    }
}
