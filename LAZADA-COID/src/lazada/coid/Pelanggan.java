/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lazada.coid;

import java.util.Scanner;
import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;

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
    public String Nama_pel;
            
    //repo untuk Id_produk agar bisa digunakan di class lain
    public int Count;
    public int Repo_IdProduk;
    public int Id_Produk;
    public String Nama_produk;
    public int Harga;
    public int jmlh;
    
    //repo bank
    public int Jmlh_Id_pay;
    public String Kode_bank;
    public String Nama_bank;
    
    //repo rekening
    public String No_rek;
    public String Atas_nama;
    
    public void Pembeli(String attention, int Id_pro) throws IOException, InterruptedException{
       
    String Pilih;
        
    Repo_IdProduk = Id_pro;
    
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
        Pembeli(strTemp, Id_pro);
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
        
        Produk pr = new Produk();
        
        String Pilih;
        
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
            
            
            //cari Id_pelanggan
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM pelanggan WHERE Email = '" +email+ "' AND Pass = '" +password+ "'";
            ResultSet rs = stmt.executeQuery(sql);
                        
            if(rs.next()){
                Nama_pel        = rs.getString("Nama_pel");
                String email_db = rs.getString("Email");
                String pass_db  = rs.getString("Pass");                
                id_pel          = rs.getString("Id_pel");
                
                System.out.println(id_pel);
                
            }
            else
                {
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();   
                    Pembeli("Maaf anda belum terdaftar", Repo_IdProduk);
                }
            
            //cari jumlah Id_pesanan yang telah terbentuk
            stmt = conn.createStatement();
            sql = "SELECT COUNT(*) FROM pesanan";
            rs = stmt.executeQuery(sql);
            
            //extract data from result set
            if(rs.next()){
                //retrieve column name                
                Count = rs.getInt("count(*)");
                //ps.amount = Count + 1;
                Count = Count + 1;
                
                //display value
                System.out.println(Count);
            }
            
            //tanya berapa yang dibeli
            Scanner sc = new Scanner(System.in);
            System.out.print("Berapa jumlah yang akan dibeli?: ");
            jmlh = sc.nextInt();
            
            System.out.println(Count);
            System.out.println(id_pel);
            System.out.println(jmlh);
            System.out.println("");
            
            stmt = conn.createStatement();
            sql = "INSERT INTO pesanan " + "VALUE (" +Count+", '" +id_pel+ "' ," +jmlh+ " , CURRENT_TIMESTAMP )";
            stmt.executeUpdate(sql);
            
            //update Id_pesanan pada tabel pesanan_produk_retail sesuai dengan Id_produk
            stmt = conn.createStatement();
            sql = "UPDATE pesanan_produk_retail " +
                    "SET Id_pesan = " +Count+ " WHERE Id_produk = " +Repo_IdProduk;
            stmt.executeUpdate(sql);
            
            //check id produk
            stmt = conn.createStatement();
            sql = "SELECT * FROM produk WHERE Id_produk = " + Repo_IdProduk;
            rs = stmt.executeQuery(sql);
            
            //extract data from result set
            int index = 1;
            while(rs.next()){
                //retrieve column name
                Id_Produk    = rs.getInt("Id_produk");
                Nama_produk = rs.getString("Nama_produk");
                Harga       = rs.getInt("Harga");
                
                //display value
                
            }
            
            System.out.println("Id Pesanan     : " +Count);
            System.out.println("Id Pelanggan   : " +id_pel);
            System.out.println("Nama Pelanggan : " +Nama_pel);
            System.out.println("Id Produk      : " +Repo_IdProduk);
            System.out.println("Nama Produk    : " +Nama_produk);
            System.out.println("Jumlah Barang  : " +jmlh);
            System.out.println("");
            //System.out.println("Total Harga    : " +jmlh*Harga);
            //System.out.println("");
             
            //lanjutkan ke pembayaran
            //new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            Pembayaran("");
 
            /*
            //enter checker
            Scanner plh = new Scanner(System.in);
            System.out.print("Apakah anda ingin belanja lagi? (y/n) : ");
            Pilih = plh.nextLine();

            System.out.println("");
            
            if(Pilih.equals("")){
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                Jawaban();
            }else
                if(Pilih.equals("y")){
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    CariBarang("");
                }else        
                    conn.close();
            */
            
    } catch (SQLException e)
        {
            System.out.println("Failed " + e.toString());
        } 
        catch(ClassNotFoundException e)
        {
                System.out.println("JDBC Driver not found");
        }
    }
    
    public void Jawaban() throws IOException, InterruptedException{
        Connection conn = null;
        Statement stmt = null;
        
        String Pilih;
        
        try {
            //register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            
            //open a connection
            //System.out.println("connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            System.out.println("Tolong masukan keyword : ");
        
            //enter checker
            Scanner plh = new Scanner(System.in);
            System.out.print("Apakah anda ingin belanja lagi? (y/n) : ");
            Pilih = plh.nextLine();
        
            if(Pilih.equals("")){
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                Jawaban();
            }else
                if(Pilih.equals("y")){
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    CariBarang("");
                }else        
                    conn.close();
            
    } catch (SQLException e)
        {
            System.out.println("Failed " + e.toString());
        } 
        catch(ClassNotFoundException e)
        {
                System.out.println("JDBC Driver not found");
        }
    }
    
    public void Pembayaran(String attention) throws IOException, InterruptedException{
        Connection conn = null;
        Statement stmt = null;
        
        String Pilih;
        
        try {
            //register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            
            //open a connection
            //System.out.println("connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            //gunakan linked list untuk menyimpan indeks = Kode_bank pada table Bank
            LinkedList<String> Id_list = new LinkedList<String>();
            
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM bank";
            ResultSet rs = stmt.executeQuery(sql);
            
            int indeks = 1;
            while(rs.next()){
               Kode_bank = rs.getString("Kode_bank");
               Nama_bank = rs.getString("Nama_bank");
               Id_list.add(Kode_bank);
               
                System.out.println(indeks+ ". " +Nama_bank+ ", Kode bank = " +Kode_bank);
                indeks++;
            }
            
            System.out.println(attention);
            System.out.println("");
            
            //enter checker
            Scanner plh = new Scanner(System.in);
            System.out.print("Pilih Bank untuk pembayaran : ");
            Pilih = plh.nextLine();
            
            if(Pilih.equals("")){
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                Pembayaran("Tolong masukan keyword!");
            }else
                if((Integer.valueOf(Pilih) - 1) < Id_list.size()){
                    Kode_bank = Id_list.get((Integer.valueOf(Pilih) - 1));
                }else   
                    {
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                        Pembayaran("Keyword yang anda masukan salah!");
                    }
            
            System.out.println("");
            
            //inisialisasi no_rek dan atas nama dari kode_bank pada table rekening
            stmt = conn.createStatement();
            sql = "SELECT * FROM rekening WHERE Kode_bank = " +Kode_bank;
            rs = stmt.executeQuery(sql);
            
            if(rs.next()){
               No_rek    = rs.getString("No_rek");
               Atas_nama = rs.getString("Atas_nama");
               
                System.out.println("No rek    = " +No_rek);
                System.out.println("Atas Nama = " +Atas_nama);
            }
            
            System.out.println("");
            
            //cari jumlah Id pembayaran pada tabel Pembayaran
            stmt = conn.createStatement();
            sql = "SELECT COUNT(*) FROM pembayaran";
            rs = stmt.executeQuery(sql);
            
            //extract data from result set
            if(rs.next()){
                //retrieve column name                
                Jmlh_Id_pay = rs.getInt("count(*)");
                //ps.amount = Count + 1;
                Jmlh_Id_pay = Jmlh_Id_pay + 1;
                
                //display value
                System.out.println(Jmlh_Id_pay);
            }
            
            System.out.println("");
            
            //isi table pembayaran
            stmt = conn.createStatement();
            sql = "INSERT INTO pembayaran " + "VALUE (" +Jmlh_Id_pay+ ", CURRENT_TIMESTAMP," +jmlh+ ", " +Count+ ", " +No_rek+ " )";
            stmt.executeUpdate(sql);
            
            //tampil pembayaran
            stmt = conn.createStatement();
            sql = "SELECT * FROM pembayaran WHERE Id_pay = " +Jmlh_Id_pay;
            stmt.executeQuery(sql);
            
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
