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
public class Produk {
    //JDBC driver name database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    //nama di localhost nya ubah sesuai nama databases
    static final String DB_URL = "jdbc:mysql://localhost/lazada";
    
    //database credential
    static final String USER = "root";
    static final String PASS = "";
    
    //repo untuk Id_produk agar bisa digunakan di class lain
    public int Count;
    public String Id_produk;
    public String Nama_produk;
    public int Harga;
    
    //repo Id_pro
    public int repo_IdPro;
    
    public void TampilProduk(int Id_pro, String attention) throws IOException, InterruptedException{
        Connection conn = null;
        Statement stmt = null;
        
        repo_IdPro = Id_pro;
        
        String Pilih;
        
        try {
            //register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            
            //open a connection
            System.out.println("connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            //execute a query
            System.out.println("creating statement...");
            System.out.println("please turn off your anti-virus...");
            
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM produk WHERE Id_produk = " + Id_pro;
            ResultSet rs = stmt.executeQuery(sql);
            
            //extract data from result set
            int index = 1;
            while(rs.next()){
                //retrieve column name
                Id_produk   = rs.getString("Id_produk");
                Nama_produk = rs.getString("Nama_produk");
                Harga       = rs.getInt("Harga");
                
                //display value
                System.out.println("Id produk \t = " +Id_produk);
                System.out.println("Index = " + index);
                System.out.println("Nama Produk \t = " +Nama_produk);
                System.out.println("Harga \t\t = " +Harga);
                System.out.println("");
                //System.out.println(pl.Id_pel);
                index += 1;
            }
            
            System.out.println("");
            System.out.println(attention);
            System.out.println("");
            System.out.println("Klik Enter untuk batal pembelian");
            System.out.print("Ketik 'beli' untuk konfirmasi pembelian : ");
            
            //enter checker
            Scanner plh = new Scanner(System.in);       
            Pilih = plh.nextLine();
            
            if(Pilih.equals("")){
                conn.close();
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                TryTampil tryn = new TryTampil();
                tryn.TampilData("");
            }
            else
                if(Pilih.equals("beli")){                   
                    //conn.close();
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    Pelanggan pl = new  Pelanggan();
                    pl.Pembeli("", repo_IdPro);
                }
                else
                    {
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                        TampilProduk(Id_pro, "Keyword salah");
                    }
            
    } catch (SQLException e)
        {
            System.out.println("Failed " + e.toString());
        } 
        catch(ClassNotFoundException e)
        {
                System.out.println("JDBC Driver not found");
        }
    }
}
