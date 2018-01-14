/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lazada.coid;

import java.util.Scanner;
import java.io.IOException;
import java.sql.*;

/**
 *
 * @author SUYATNA-PC
 */
public class Pesanan {
    //JDBC driver name database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    //nama di localhost nya ubah sesuai nama databases
    static final String DB_URL = "jdbc:mysql://localhost/lazada";
    
    //database credential
    static final String USER = "root";
    static final String PASS = "";
        
    String Pilih;
    int jmlh;
    public int amount;
    public String _IdPel;
 
    public void Pesan(String attention) throws IOException, InterruptedException{
        Connection conn = null;
        Statement stmt = null;
        
        try {
            //register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            
            //open a connection
            System.out.println("connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            //execute a query
            System.out.println("creating statement...");
            System.out.println("please turn off your anti-virus...");
            System.out.println("");
            
            /*
            Scanner sc = new Scanner(System.in);
            System.out.print("Berapa jumlah yang akan dibeli?: ");
            jmlh = sc.nextInt();
            
            System.out.println(amount);
            System.out.println(_IdPel);
            System.out.println(jmlh);
            
            stmt = conn.createStatement();
            String sql;
            sql = "INSERT INTO pesanan " + "VALUE (" +amount+", '" +_IdPel+ "' ," +jmlh+ ")";
            stmt.executeUpdate(sql);
            */
            
            conn.close();
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();   
            Tampil();
            
    } catch (SQLException se)
        {
            //handle errors for jdbc
            se.printStackTrace();
        } 
        catch(Exception e)
        {
            //handle errors for Class.forName
            e.printStackTrace();
        }
    }
    
    public void Tampil() throws IOException, InterruptedException{
        
    }
}
