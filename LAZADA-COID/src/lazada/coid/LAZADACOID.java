/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lazada.coid;
//import java.sql.*;
import java.io.IOException;
import java.util.Scanner;
/**
 *
 * @author SUYATNA-PC
 */
public class LAZADACOID {
    /*
    //JDBC driver name database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    //nama di localhost nya ubah sesuai nama databases
    static final String DB_URL = "jdbc:mysql://localhost/lazada";
    
    //database credential
    static final String USER = "root";
    static final String PASS = "";
    */
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException{
        // TODO code application logic here
        //Connection conn = null;
        //Statement stmt = null;
        
        //inisiailsasi pengambilan class
        TryTampil tryn = new TryTampil();
        tryn.TampilData("");
        
        /*
        try {
            //register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            
            //open a connection
            System.out.println("connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            //execute a query
            System.out.println("creating statement...");
            
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM pelanggan";
            ResultSet rs = stmt.executeQuery(sql);
            
            System.out.println("");
            
            //extract data from result set
            while(rs.next()){
                //retrieve column name
                int Id_pel      = rs.getInt("Id_pel");
                String Nama_pel = rs.getString("Nama_pel");
                String Email    = rs.getString("Email");
                String No_telp  = rs.getString("No_telp");
                String Alamat   = rs.getString("Alamat");
                
                //display value
                System.out.println("Id_pel \t\t = " +Id_pel);
                System.out.println("Nama_pel \t = " +Nama_pel);
                System.out.println("Email \t\t = " +Email);
                System.out.println("No_telp \t = " +No_telp);
                System.out.println("Alamat \t\t = " +Alamat);
                System.out.println("");
            }
            
            System.out.println("");
            
            //use script
            tryn.TampilData();
            
            conn.close();
            
        } catch (SQLException e) {
            System.out.println("Failed " + e.toString());
        }
        catch(ClassNotFoundException e){
            System.out.println("JDBC Driver not found");
        }
        */
    }   
}
