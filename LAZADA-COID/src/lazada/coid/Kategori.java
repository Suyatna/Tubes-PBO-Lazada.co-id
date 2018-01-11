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
public class Kategori {
    //JDBC driver name database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    //nama di localhost nya ubah sesuai nama databases
    static final String DB_URL = "jdbc:mysql://localhost/lazada";
    
    //database credential
    static final String USER = "root";
    static final String PASS = "";
    
    public void TampilKategori(String attention) throws IOException, InterruptedException{
        String Pilih;
        
        System.out.println("Pilih Kategori : ");
        //kode jmkc  = Jam Tangan, Kacamata dan Pakaian
        System.out.println("1. Jam Tangan, Kacamata dan Pakaian");
        //kode kolap = Komputer dan Laptop
        System.out.println("2. Komputer dan Laptop");
        //kode memub = Media Musik dan Buku
        System.out.println("3. Media Musik dan Buku");
        //kode altkj = Alat Tulis dan Kerajinan
        System.out.println("4. Alat Tulis dan Kerajinan");
        System.out.println("");
        
        System.out.println(attention);
        
        System.out.println("");
        System.out.print("Pilih : ");
               
        //enter checker
        Scanner plh = new Scanner(System.in);       
        Pilih = plh.nextLine();
        System.out.print("Pilih : ");
             
        if(Pilih.equals("")){
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            String strTemp = "Tolong isi pilihannya";
            TampilKategori(strTemp);
        }
        else
            if(Pilih.equals("1")){
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                jmkc("");
            }
            else
                if(Pilih.equals("2")){
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    kolap();
                }
                else
                    if(Pilih.equals("3")){
                       new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                       memub();
                    }
    }
    
    //kode jmkc  = Jam Tangan, Kacamata dan Pakaian
    public void jmkc(String attention) throws IOException, InterruptedException{
        Connection conn = null;
        Statement stmt = null;
        
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
            sql = "SELECT Id_cate, Nama_produk, Harga FROM produk WHERE Id_cate = 01";
            ResultSet rs = stmt.executeQuery(sql);
            
            //extract data from result set
            int index = 1;
            while(rs.next()){
                //retrieve column name
                //int Id_produk      = rs.getInt("Id_produk");
                String Nama_produk = rs.getString("Nama_produk");
                String Harga       = rs.getString("Harga");
                //String Id_cate     = rs.getString("Id_cate");
                
                //display value
                //System.out.println("Id produk \t = " +Id_produk);
                System.out.println("Index = " + index);
                System.out.println("Nama Produk \t = " +Nama_produk);
                System.out.println("Harga \t\t = " +Harga);
                System.out.println("");
                index += 1;
            }
            
            System.out.println("");
            System.out.println("Klik Enter untuk kembali ke menu");
            System.out.println("Ketik nomor untuk melihat barang : ");
            System.out.println("");
            
            //enter checker
            Scanner plh = new Scanner(System.in);       
            Pilih = plh.nextLine();
            
            if(Pilih.equals("")){
                conn.close();
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                TampilKategori("");
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
    
    //kode kolap = Komputer dan Laptop
    public void kolap() throws IOException, InterruptedException{
        Connection conn = null;
        Statement stmt = null;
        
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
            sql = "SELECT Id_cate, Nama_produk, Harga FROM produk WHERE Id_cate = 02";
            ResultSet rs = stmt.executeQuery(sql);
            
            //extract data from result set
            int index = 1;
            while(rs.next()){
                //retrieve column name
                //int Id_produk      = rs.getInt("Id_produk");
                String Nama_produk = rs.getString("Nama_produk");
                String Harga       = rs.getString("Harga");
                //String Id_cate     = rs.getString("Id_cate");
                
                //display value
                //System.out.println("Id produk \t = " +Id_produk);
                System.out.println("Index = " + index);
                System.out.println("Nama Produk \t = " +Nama_produk);
                System.out.println("Harga \t\t = " +Harga);
                System.out.println("");
                index += 1;
            }
            
            System.out.println("");
            System.out.println("Klik Enter untuk kembali ke menu");
            System.out.println("Ketik nomor untuk melihat barang : ");
            System.out.println("");
            
            //enter checker
            Scanner plh = new Scanner(System.in);       
            Pilih = plh.nextLine();
            
            if(Pilih.equals("")){
                conn.close();
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                TampilKategori("");
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
    
    //kode memub = Media Musik dan Buku
    public void memub() throws IOException, InterruptedException{
        Connection conn = null;
        Statement stmt = null;
        
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
            sql = "SELECT Id_cate, Nama_produk, Harga FROM produk WHERE Id_cate = 03";
            ResultSet rs = stmt.executeQuery(sql);
            
            //extract data from result set
            int index = 1;
            while(rs.next()){
                //retrieve column name
                //int Id_produk      = rs.getInt("Id_produk");
                String Nama_produk = rs.getString("Nama_produk");
                String Harga       = rs.getString("Harga");
                //String Id_cate     = rs.getString("Id_cate");
                
                //display value
                //System.out.println("Id produk \t = " +Id_produk);
                System.out.println("Index = " + index);
                System.out.println("Nama Produk \t = " +Nama_produk);
                System.out.println("Harga \t\t = " +Harga);
                System.out.println("");
                index += 1;
            }
            
            System.out.println("");
            System.out.println("Klik Enter untuk kembali ke menu");
            System.out.println("Ketik nomor untuk melihat barang : ");
            System.out.println("");
            
            //enter checker
            Scanner plh = new Scanner(System.in);       
            Pilih = plh.nextLine();
            
            if(Pilih.equals("")){
                conn.close();
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                TampilKategori("");
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
