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
public class retail {
    //JDBC driver name database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    //nama di localhost nya ubah sesuai nama databases
    static final String DB_URL = "jdbc:mysql://localhost/lazada";
    
    //database credential
    static final String USER = "root";
    static final String PASS = "";
    
    String email;
    String password;
    
    //repo retail
    String Id_retail;
    String Nama_retail;
    
    int Jmlh_Id_retail;
    
    //repo produk database
        String Id_produk;
        String Nama_produk;
        int Harga;
        String Id_cate;
        int Jmlh_Id_produk;
        
        //repo produk input
        String Nama_produk_in;
        int Harga_in;
        
        //repo kategori
        String Id_kategori;
        String Nama_kategori;
    
    public void Tampil(String attention) throws IOException, InterruptedException{
        
        String Pilih;
        
        System.out.println("Retail : ");
        System.out.println("1. Masuk");
        System.out.println("2. Login");
        System.out.println("");
        
        System.out.println(attention);
        System.out.println("");
        
        Scanner sc = new Scanner(System.in);
        System.out.print("Pilih : ");
        Pilih = sc.nextLine();
        
        if(Pilih.equals("")){
             new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
             Tampil("Masukan keyword");
        }
        else
            if(Pilih.equals("1")){
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                Masuk("");
            }
            else
                if(Pilih.equals("2")){
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    Daftar("");
                }
                else
                    {
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                        Tampil("Keyword yang anda masukan salah!");
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
            System.out.println("");
            
            //cari retail
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM retail WHERE Email = '" +email+ "' AND Pass = '" +password+ "'";
            ResultSet rs = stmt.executeQuery(sql);
                        
            if(rs.next()){
                Nama_retail        = rs.getString("Nama_retail");
                String email_db = rs.getString("Email");
                String pass_db  = rs.getString("Pass");                
                Id_retail         = rs.getString("Id_retail");
                
                System.out.println(Id_retail);
                System.out.println("");
            }
            else
                {
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();   
                    Tampil("Anda belum terdaftar");
                }
            
            //tampil produk retail
            stmt = conn.createStatement();
            sql = "SELECT produk.`Id_produk`, produk.`Nama_produk`, produk.`Harga`, produk.`Id_cate`, produk_retail.`Id_retail`\n" +
                  "FROM produk\n" +
                  "INNER JOIN produk_retail on produk.`Id_produk` = produk_retail.`Id_produk`\n" +
                  "WHERE produk_retail.`Id_retail` = " +Id_retail;
            rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                String Id_produk   = rs.getString("Id_produk");
                String Nama_produk = rs.getString("Nama_produk");
                int Harga          = rs.getInt("Harga");
                String Id_cate     = rs.getString("Id_cate");
                String Id_retail   = rs.getString("Id_retail");
                
                System.out.println("Id Produk   \t : " +Id_produk);
                System.out.println("Nama Produk \t : " +Nama_produk);
                System.out.println("Harga       \t : " +Harga);
                System.out.println("Id Kategori \t : " +Id_cate);
                System.out.println("");
            }
            System.out.println("");
            
            Scanner sc = new Scanner(System.in);
            System.out.println("Tekan Enter untuk melanjutkan...");
            Pilih = sc.nextLine();
            
            if(Pilih.equals("")){
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                Detail_retail("");
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
    
    public void Detail_retail(String attention) throws IOException, InterruptedException{
        
        String Pilih;
        
        System.out.println("Detail Retail : ");
        System.out.println("1. Tambah Barang");
        System.out.println("2. Lihat Barang Yang Sudah Dipesan");
        System.out.println("0. Keluar");
        System.out.println("");
        
        System.out.println(attention);
        System.out.println("");
        
        Scanner sc = new Scanner(System.in);
        System.out.print("Pilih : ");
        Pilih = sc.nextLine();
        
        if(Pilih.equals("")){
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            Detail_retail("Masukan keyword");
        }
        else
            if(Pilih.equals("1")){
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                Tambah_barang("");
            }
            else
                if(Pilih.equals("2")){
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                }
                else
                    if(Pilih.equals("0")){
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                        TryTampil ty = new TryTampil();
                        ty.TampilData("");
                    }
                    else
                        {
                            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                            Detail_retail("Keyword yang anda masukan salah!");
                        }
    }
    
    public void Tambah_barang(String attention) throws IOException, InterruptedException{
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
            System.out.println("");
            
            System.out.println(attention);
            System.out.println("");
            
            Scanner sc = new Scanner(System.in);
            System.out.print("Nama Produk  \t: ");
            Nama_produk_in = sc.nextLine();
            
            Scanner sc2 = new Scanner(System.in);
            System.out.print("Harga        \t: ");
            Harga_in = sc2.nextInt();
            
            //tampil kategori
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM kategori";
            ResultSet rs = stmt.executeQuery(sql);
            
            //using linked list to stored data Id_cate
            LinkedList<String> Id_list = new LinkedList<String>();
            
            while(rs.next()){
                String Id_cate_out   = rs.getString("Id_cate");
                String Nama_cate_out = rs.getString("Nama_cate");
                
                Id_list.add(Id_cate_out);
                
                System.out.println("Id Kategori   : " +Id_cate_out);
                System.out.println("Nama Kategori : " +Nama_cate_out);
                System.out.println("");
            }
            
            //enter checker
            Scanner plh = new Scanner(System.in);
            System.out.print("Pilih Kategori : ");
            Pilih = plh.nextLine();
            
            if(Pilih.equals("")){
                conn.close();
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                Tambah_barang("Pilihan gagal!");
            }else
                if ((Integer.valueOf(Pilih) - 1) < Id_list.size()){
                    Id_kategori = Id_list.get(Integer.valueOf(Pilih));
                }
                else 
                {
                    System.out.println("");
                    System.out.println("Indeks yang dipilih lebih dari " +Id_list.size() +" barang yang ada!");
                    System.out.println("");
                }
            
            //lihat jumlah produk + 7000
            stmt = conn.createStatement();
            sql = "SELECT COUNT(*) FROM produk";
            rs = stmt.executeQuery(sql);
            
            //extract data from result set
            if(rs.next()){
                //retrieve column name                
                Jmlh_Id_produk = rs.getInt("count(*)");
                
                Jmlh_Id_produk = Jmlh_Id_produk + 7000;
                
                //display value
                System.out.println(Jmlh_Id_produk);
                System.out.println("");
            }
            
            //simpan data ke table produk
            stmt = conn.createStatement();
            sql = "INSERT INTO produk " +
                    "VALUES ('" +Jmlh_Id_produk+ "', '" +Nama_produk_in+ "', " +Harga_in+ ", " +Id_kategori+ ")";
            stmt.executeUpdate(sql);
            
            //simpan data ke table produk_retail
            stmt = conn.createStatement();
            sql = "INSERT INTO produk_retail " +
                    "VALUES ('" +Jmlh_Id_produk+ "', '" +Id_retail+ "')";
            stmt.executeUpdate(sql);
            
            //simpan data ke pesanan_produk_retail
            stmt = conn.createStatement();
            sql = "INSERT INTO pesanan_produk_retail (Id_produk, Id_retail) " +
                    "VALUES ('" +Jmlh_Id_produk+ "', '" +Id_retail+ "')";
            stmt.executeUpdate(sql);
            
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            //clear linked list
            Id_list.clear();
            Detail_retail("Data sudah ditambahkan");
            
    } catch (SQLException e)
        {
            System.out.println("Failed " + e.toString());
        } 
        catch(ClassNotFoundException e)
        {
                System.out.println("JDBC Driver not found");
        }
    }
    
    public void Lihat_produk_pesan(String attention) throws IOException, InterruptedException{
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
            //System.out.println("creating statement...");
            //System.out.println("please turn off your anti-virus...");
            
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT produk.`Id_produk`, produk.`Nama_produk`, produk.`Harga`, pesanan_produk_retail.`Id_pesan`, pesanan_produk_retail.`Id_retail`\n" +
                  "FROM produk\n" +
                  "INNER JOIN pesanan_produk_retail on produk.`Id_produk` = pesanan_produk_retail.`Id_produk`\n" +
                  "WHERE pesanan_produk_retail.`Id_pesan` IS NOT NULL  AND pesanan_produk_retail.`Id_retail` = " +Id_retail;
            
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                String Id_produk_out   = rs.getString("Id_produk");
                String Nama_produk_out = rs.getString("Nama_produk");
                int Harga_out          = rs.getInt("Harga");
                String Id_pesan_out    = rs.getString("Id_pesan");
                String Id_retail_out   = rs.getString("Id_retail");
                
                System.out.println("Id Produk   \t : " +Id_produk_out);
                System.out.println("Nama Produk \t : " +Nama_produk_out);
                System.out.println("Harga       \t : " +Harga_out);
                System.out.println("Id Pesanan  \t : " +Id_pesan_out);
                System.out.println("Id Retail   \t : " +Id_retail_out);
                System.out.println("");
            }
            
            Scanner sc = new Scanner(System.in);
            System.out.println("Tekan Enter untuk kembali...");
            Pilih = sc.nextLine();
            
            if(Pilih.equals("")){
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                Tampil("");
            }
            
        }catch (SQLException e)
        {
            System.out.println("Failed " + e.toString());
        } 
        catch(ClassNotFoundException e)
        {
            System.out.println("JDBC Driver not found");
        }
    }
    
    public void Daftar(String attention) throws IOException, InterruptedException{
        Connection conn = null;
        Statement stmt = null;
        
        String Pilih;
        
        String Nama;
        String Nmr_telp;
        String Alamat;
        String Email;
        String Password;
        
        try {
            //register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            
            //open a connection
            System.out.println("connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            //execute a query
            //System.out.println("creating statement...");
            //System.out.println("please turn off your anti-virus...");
            
            Scanner nm = new Scanner(System.in);
            System.out.print("Nama Lengkap = ");
            Nama = nm.nextLine();
            
            Scanner em = new Scanner(System.in);
            System.out.print("Email        = ");
            Email = em.nextLine();
            
            Scanner pass = new Scanner(System.in);
            System.out.print("Password     = ");
            Password = pass.nextLine();
            
            Scanner alm = new Scanner(System.in);
            System.out.print("Alamat       = ");
            Alamat = alm.nextLine();
            
            Scanner nmr = new Scanner(System.in);
            System.out.print("Nomor telp   = ");
            Nmr_telp = nmr.nextLine();
            
            //cari jumlah Id_retail
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT COUNT(*) FROM retail";
            ResultSet rs = stmt.executeQuery(sql);
            
            //extract data from result set
            if(rs.next()){
                //retrieve column name                
                Jmlh_Id_retail = rs.getInt("count(*)");
                //ps.amount = Count + 1;
                Jmlh_Id_retail = Jmlh_Id_retail + 10000;
                
                //display value
                System.out.println(Jmlh_Id_retail);
                System.out.println("");
            }
            
            //cari email yang tidak sama
            stmt = conn.createStatement();
            sql = "SELECT * FROM retail WHERE Email = '" +Email+ "' ";
            rs = stmt.executeQuery(sql);
                        
            if(rs.next()){
                Nama_retail     = rs.getString("Nama_pel");
                String email_db = rs.getString("Email");
                String pass_db  = rs.getString("Pass");                
                Id_retail       = rs.getString("Id_pel");
                
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();   
                Tampil("Maaf anda sudah terdaftar!");
            }
            else
                {
                    //daftarkan email
                    stmt = conn.createStatement();
                    sql = "INSERT INTO pelanggan " +
                            "VALUES (" +Jmlh_Id_retail+ ", '" +Nama+ "', '" +Nmr_telp+ "', '" +Alamat+ "', '" +Email+ "', '" +Password+ "')";
                    stmt.executeUpdate(sql);
                    
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();   
                    Tampil("Anda sudah terdaftar");
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
