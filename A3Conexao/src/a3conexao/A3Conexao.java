/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package a3conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author noobg
 */
public class A3Conexao {
public static void main(String[] args) {
    
}
    private static final String URL  = "jdbc:mysql://localhost:3306/sapataria";
    private static final String USER     = "root";
    private static final String PASSWORD = "070976Jp!";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("Driver JDBC não encontrado", ex);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
     
          
      }
    
    

