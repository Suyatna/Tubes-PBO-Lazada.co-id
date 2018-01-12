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
public class Pelanggan {
    //JDBC driver name database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    //nama di localhost nya ubah sesuai nama databases
    static final String DB_URL = "jdbc:mysql://localhost/lazada";
    
    //database credential
    static final String USER = "root";
    static final String PASS = "";
    
    //repo input email dan password
    public String email;
    public String password;
    public String id_pel;
    
    //repo untuk Id_produk agar bisa digunakan di class lain
    public int Count;
    public String Id_produk;
    
    public void Pembeli(String attention) throws IOException, InterruptedException{
       
    String Pilih;
        
    System.out.println("Pembeli : ");
    System.out.println("1. Masuk");
    System.out.println("2. Daftar");
    System.out.println("0. Kembali");
    System.out.println("");
        
    System.out.println(attention);
        
    System.out.println("");
        
    System.out.print("Pilih : ");
        
    //enter checker
    Scanner plh = new Scanner(System.in); 
    Pilih = plh.nextLine();
        
    if(Pilih.equals("")){
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        String strTemp = "Tolong isi pilihannya";
        Pembeli(strTemp);
    }
    else
        if(Pilih.equals("1")){
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            Masuk("");
        }
    }
    
    public void CariBarang(String attention) throws IOException, InterruptedException{
        String Pilih;
        
        System.out.println("Cari Barang : ");
        System.out.println("1. Kategori");
        System.out.println("2. Rekomendasi");
        System.out.println("0. Kembali");
        System.out.println("");
        
        System.out.println(attention);
        
        System.out.println("");
        
        System.out.print("Pilih : ");

        //enter checker
        Scanner plh = new Scanner(System.in); 
        Pilih = plh.nextLine();
        
        if(Pilih.equals("")){
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            String strTemp = "Tolong isi pilihannya";
            CariBarang(strTemp);
        }
        else
            if(Pilih.equals("1")){
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                //use class kategori
                Kategori ktg = new Kategori();
                ktg.TampilKategori("");
            }
            else
                if(Pilih.equals("2")){
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    Kategori ktg = new Kategori();
                    ktg.SemuaProduk();
                }
                else 
                    if(Pilih.equals("0")){
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();   
                        TryTampil tryn = new TryTampil();
                        tryn.TampilData("");
                    }
    }
    
    public void Masuk(String attention) throws IOException, InterruptedException{
        
        Connection conn = null;
        Statement stmt = null;
        
        Scanner ema = new  Scanner(System.in);
        System.out.print("Email \t\t: ");
        email = ema.nextLine();
        
        Scanner pass = new Scanner(System.in);
        System.out.print("Password \t: ");
        password = pass.nextLine(); 
        
        Pesanan ps = new Pesanan();
        
        try {
            //register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            
            //open a connection
            System.out.println("connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            //execute a query
            //System.out.println("creating statement...");
            System.out.println("please turn off your anti-virus...");
            System.out.println("");
            
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM pelanggan WHERE Email = '" +email+ "' AND Pass = '" +password+ "'";
            ResultSet rs = stmt.executeQuery(sql);
                        
            if(rs.next()){
                String email_db = rs.getString("Email");
                String pass_db  = rs.getString("Pass");                
                id_pel          = rs.getString("Id_pel");
                ps._IdPel = id_pel;
                
                System.out.println(id_pel);
                
            }
            else
                {
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();   
                    Pembeli("Maaf anda belum terdaftar");
                }
            
            stmt = conn.createStatement();
            sql = "SELECT COUNT(*) FROM pesanan";
            rs = stmt.executeQuery(sql);
            
            //extract data from result set
            if(rs.next()){
                //retrieve column name                
                Count = rs.getInt("count(*)");
                ps.amount = Count + 1;
                //display value
                //System.out.println(Count);
            }
            
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();   
            ps.Pesan("");
            
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
