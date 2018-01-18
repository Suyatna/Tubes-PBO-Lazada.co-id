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
public class Kategori {
    //JDBC driver name database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    //nama di localhost nya ubah sesuai nama databases
    static final String DB_URL = "jdbc:mysql://localhost/lazada";
    
    //database credential
    static final String USER = "root";
    static final String PASS = "";
    
    public int Id_produk;
    public String Nama_produk;
    public int Harga_produk;
    
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
                    kolap("");
                }
                else
                    if(Pilih.equals("3")){
                       new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                       memub("");
                    }else 
                        if(Pilih.equals("4")){
                            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                            altjk("");
                         }else
                            {
                                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                                TampilKategori("Keyword yang anda masukan salah!");
                            }
    }
    
    //kode jmkc  = Jam Tangan, Kacamata dan Pakaian
    public void jmkc(String attention) throws IOException, InterruptedException{
        Connection conn = null;
        Statement stmt = null;
        
        String Pilih;
        String Pilih2;
        
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
            
            System.out.println("Pengurutan berdasarkan : ");
            System.out.println("1. Harga Terbesar");
            System.out.println("2. Harga Terkecil");
            System.out.println("");
            
            System.out.println(attention);
            System.out.println("");
            
            Scanner plh2 = new Scanner(System.in);
            System.out.print("Pilih : ");
            Pilih2 = plh2.nextLine();
            
            stmt = conn.createStatement();
            String sql;
            sql = "";
            
            if(Pilih2.equals("")){
                conn.close();
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                jmkc("Masukan keyword");
            }
            else
                if(Pilih2.equals("1")){
                    sql = "SELECT produk.`Id_produk`, produk.`Nama_produk`, produk.`Harga`, produk.`Id_cate`, pesanan_produk_retail.`Id_pesan`\n" +
                          "FROM produk\n" +
                          "INNER JOIN pesanan_produk_retail on produk.`Id_produk` = pesanan_produk_retail.`Id_produk`\n" +
                          "WHERE pesanan_produk_retail.`Id_pesan` IS NULL AND produk.`Id_cate` = 01 ORDER BY produk.`Harga` DESC;";
                }
                else
                    if(Pilih2.equals("2")){
                        sql = "SELECT produk.`Id_produk`, produk.`Nama_produk`, produk.`Harga`, produk.`Id_cate`, pesanan_produk_retail.`Id_pesan`\n" +
                          "FROM produk\n" +
                          "INNER JOIN pesanan_produk_retail on produk.`Id_produk` = pesanan_produk_retail.`Id_produk`\n" +
                          "WHERE pesanan_produk_retail.`Id_pesan` IS NULL AND produk.`Id_cate` = 01 ORDER BY produk.`Harga` ASC;";
                    }
                    else
                        {
                            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                            jmkc("Keyword salah!");
                        }
                                    
            ResultSet rs = stmt.executeQuery(sql);
            
            //extract data from result set
            int index = 1;
            
            //using linked list to stored data Id_produk
            LinkedList<Integer> Id_list = new LinkedList<Integer>();
            
            while(rs.next()){
                //retrieve column name
                Id_produk      = rs.getInt("Id_produk");
                String Nama_produk = rs.getString("Nama_produk");
                String Harga       = rs.getString("Harga");
                
                Id_list.add(Id_produk);
                
                //display value
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
            }else
                if ((Integer.valueOf(Pilih) - 1) < Id_list.size()){
                    conn.close();
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    Produk prd = new Produk();
                    prd.TampilProduk(Id_list.get(Integer.valueOf(Pilih) - 1), "");
                }
                else 
                {
                    System.out.println("");
                    System.out.println("Indeks yang dipilih lebih dari " +Id_list.size() +" barang yang ada!");
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
    public void kolap(String attention) throws IOException, InterruptedException{
        Connection conn = null;
        Statement stmt = null;
        
        String Pilih;
        String Pilih2;
        
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
            
            System.out.println("Pengurutan berdasarkan : ");
            System.out.println("1. Harga Terbesar");
            System.out.println("2. Harga Terkecil");
            System.out.println("");
            
            System.out.println(attention);
            System.out.println("");
            
            Scanner plh2 = new Scanner(System.in);
            System.out.print("Pilih : ");
            Pilih2 = plh2.nextLine();
            
            stmt = conn.createStatement();
            String sql;
            sql = "";
            
            if(Pilih2.equals("")){
                conn.close();
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                jmkc("Masukan keyword");
            }
                else
                    if(Pilih2.equals("1")){
                        sql = "SELECT produk.`Id_produk`, produk.`Nama_produk`, produk.`Harga`, produk.`Id_cate`, pesanan_produk_retail.`Id_pesan`\n" +
                              "FROM produk\n" +
                              "INNER JOIN pesanan_produk_retail on produk.`Id_produk` = pesanan_produk_retail.`Id_produk`\n" +
                              "WHERE pesanan_produk_retail.`Id_pesan` IS NULL AND produk.`Id_cate` = 02 ORDER BY produk.`Harga` DESC;";
                    }
                        else
                            if(Pilih2.equals("2")){
                                sql = "SELECT produk.`Id_produk`, produk.`Nama_produk`, produk.`Harga`, produk.`Id_cate`, pesanan_produk_retail.`Id_pesan`\n" +
                                      "FROM produk\n" +
                                      "INNER JOIN pesanan_produk_retail on produk.`Id_produk` = pesanan_produk_retail.`Id_produk`\n" +
                                      "WHERE pesanan_produk_retail.`Id_pesan` IS NULL AND produk.`Id_cate` = 02 ORDER BY produk.`Harga` ASC;";
                            }
                            else
                                {
                                    conn.close();
                                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                                    jmkc("Masukan keyword");
                                }
                
            ResultSet rs = stmt.executeQuery(sql);
            
            //extract data from result set
            int index = 1;
            
            //using linked list to stored data Id_produk
            LinkedList<Integer> Id_list = new LinkedList<Integer>();
            
            while(rs.next()){
                //retrieve column name
                int Id_produk      = rs.getInt("Id_produk");
                String Nama_produk = rs.getString("Nama_produk");
                String Harga       = rs.getString("Harga");
                //String Id_cate     = rs.getString("Id_cate");
                
                Id_list.add(Id_produk);
                
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
            
            //enter checker
            Scanner plh = new Scanner(System.in);       
            Pilih = plh.nextLine();
            
            if(Pilih.equals("")){
                conn.close();
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                TampilKategori("");
            }else
                if ((Integer.valueOf(Pilih) - 1) < Id_list.size()){
                    conn.close();
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    Produk prd = new Produk();
                    prd.TampilProduk(Id_list.get(Integer.valueOf(Pilih) - 1), "");
                }
                else 
                {
                    System.out.println("");
                    System.out.println("Indeks yang dipilih lebih dari " +Id_list.size() +" barang yang ada!");
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
    public void memub(String attention) throws IOException, InterruptedException{
        Connection conn = null;
        Statement stmt = null;
        
        String Pilih;
        String Pilih2;
        
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
            
            System.out.println("Pengurutan berdasarkan : ");
            System.out.println("1. Harga Terbesar");
            System.out.println("2. Harga Terkecil");
            System.out.println("");
            
            System.out.println(attention);
            System.out.println("");
            
            Scanner plh2 = new Scanner(System.in);
            System.out.print("Pilih : ");
            Pilih2 = plh2.nextLine();
            
            stmt = conn.createStatement();
            String sql;
            sql = "";
            
            if(Pilih2.equals("")){
                conn.close();
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                memub("Masukan keyword");
            }
            else
                if(Pilih2.equals("1")){
                    sql = "SELECT produk.`Id_produk`, produk.`Nama_produk`, produk.`Harga`, produk.`Id_cate`, pesanan_produk_retail.`Id_pesan`\n" +
                          "FROM produk\n" +
                          "INNER JOIN pesanan_produk_retail on produk.`Id_produk` = pesanan_produk_retail.`Id_produk`\n" +
                          "WHERE pesanan_produk_retail.`Id_pesan` IS NULL AND produk.`Id_cate` = 03 ORDER BY produk.`Harga` DESC;";
                }
                else
                    if(Pilih2.equals("2")){
                        sql = "SELECT produk.`Id_produk`, produk.`Nama_produk`, produk.`Harga`, produk.`Id_cate`, pesanan_produk_retail.`Id_pesan`\n" +
                          "FROM produk\n" +
                          "INNER JOIN pesanan_produk_retail on produk.`Id_produk` = pesanan_produk_retail.`Id_produk`\n" +
                          "WHERE pesanan_produk_retail.`Id_pesan` IS NULL AND produk.`Id_cate` = 03 ORDER BY produk.`Harga` ASC;";
                    }
                    else
                        {
                            conn.close();
                            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                            memub("Keyword salah!");
                        }
            
            
            ResultSet rs = stmt.executeQuery(sql);
            
            //extract data from result set
            int index = 1;
            
            //using linked list to stored data Id_produk
            LinkedList<Integer> Id_list = new LinkedList<Integer>();
            
            while(rs.next()){
                //retrieve column name
                int Id_produk      = rs.getInt("Id_produk");
                String Nama_produk = rs.getString("Nama_produk");
                String Harga       = rs.getString("Harga");
                
                Id_list.add(Id_produk);
                
                //display value
                System.out.println("Index = " + index);
                System.out.println("Nama Produk \t = " +Nama_produk);
                System.out.println("Harga \t\t = " +Harga);
                System.out.println("");
                index += 1;
            }
            
            System.out.println("");
            System.out.println("Klik Enter untuk kembali ke menu");
            System.out.println("Ketik nomor untuk melihat barang : ");
            
            //enter checker
            Scanner plh = new Scanner(System.in);       
            Pilih = plh.nextLine();
            
            if(Pilih.equals("")){
                conn.close();
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                TampilKategori("");
            }else
                if ((Integer.valueOf(Pilih) - 1) < Id_list.size()){
                    conn.close();
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    Produk prd = new Produk();
                    prd.TampilProduk(Id_list.get(Integer.valueOf(Pilih) - 1), "");
                }
                else 
                {
                    System.out.println("");
                    System.out.println("Indeks yang dipilih lebih dari " +Id_list.size() +" barang yang ada!");
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
    
    public void altjk(String attention) throws IOException, InterruptedException{
        Connection conn = null;
        Statement stmt = null;
        
        String Pilih;
        String Pilih2;
        
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
            
             System.out.println("Pengurutan berdasarkan : ");
            System.out.println("1. Harga Terbesar");
            System.out.println("2. Harga Terkecil");
            System.out.println("");
            
            System.out.println(attention);
            System.out.println("");
            
            Scanner plh2 = new Scanner(System.in);
            System.out.print("Pilih : ");
            Pilih2 = plh2.nextLine();
            
            stmt = conn.createStatement();
            String sql;
            sql = "";
            
            if(Pilih2.equals("")){
                conn.close();
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                altjk("Masukan keyword");
            }
            else
                if(Pilih2.equals("1")){
                    sql = "SELECT produk.`Id_produk`, produk.`Nama_produk`, produk.`Harga`, produk.`Id_cate`, pesanan_produk_retail.`Id_pesan`\n" +
                          "FROM produk\n" +
                          "INNER JOIN pesanan_produk_retail on produk.`Id_produk` = pesanan_produk_retail.`Id_produk`\n" +
                          "WHERE pesanan_produk_retail.`Id_pesan` IS NULL AND produk.`Id_cate` = 04 ORDER BY produk.`Harga` DESC;";
                }
                else
                    if(Pilih2.equals("2")){
                        sql = "SELECT produk.`Id_produk`, produk.`Nama_produk`, produk.`Harga`, produk.`Id_cate`, pesanan_produk_retail.`Id_pesan`\n" +
                              "FROM produk\n" +
                              "INNER JOIN pesanan_produk_retail on produk.`Id_produk` = pesanan_produk_retail.`Id_produk`\n" +
                              "WHERE pesanan_produk_retail.`Id_pesan` IS NULL AND produk.`Id_cate` = 04 ORDER BY produk.`Harga` ASC;";
                    }
                    else
                        {
                            conn.close();
                            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                            altjk("Keyword salah!S");
                        }
            
            
            ResultSet rs = stmt.executeQuery(sql);
            
            //extract data from result set
            int index = 1;
            
            //using linked list to stored data Id_produk
            LinkedList<Integer> Id_list = new LinkedList<Integer>();
            
            while(rs.next()){
                //retrieve column name
                int Id_produk      = rs.getInt("Id_produk");
                String Nama_produk = rs.getString("Nama_produk");
                String Harga       = rs.getString("Harga");
                //String Id_cate     = rs.getString("Id_cate");
                
                Id_list.add(Id_produk);
                
                //display value
                System.out.println("Index = " + index);
                System.out.println("Nama Produk \t = " +Nama_produk);
                System.out.println("Harga \t\t = " +Harga);
                System.out.println("");
                index += 1;
            }
            
            System.out.println("");
            System.out.println("Klik Enter untuk kembali ke menu");
            System.out.println("Ketik nomor untuk melihat barang : ");
            
            //enter checker
            Scanner plh = new Scanner(System.in);       
            Pilih = plh.nextLine();
            
            if(Pilih.equals("")){
                conn.close();
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                TampilKategori("");
            }else
                if ((Integer.valueOf(Pilih) - 1) < Id_list.size()){
                    conn.close();
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    Produk prd = new Produk();
                    prd.TampilProduk(Id_list.get(Integer.valueOf(Pilih) - 1), "");
                }
                else 
                {
                    System.out.println("");
                    System.out.println("Indeks yang dipilih lebih dari " +Id_list.size() +" barang yang ada!");
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
    
    
    public void SemuaProduk(String attention) throws IOException, InterruptedException{
        Connection conn = null;
        Statement stmt = null;
        
        String Pilih;
        String Pilih2;
        
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
            
            System.out.println("Pengurutan berdasarkan : ");
            System.out.println("1. Harga Terbesar");
            System.out.println("2. Harga Terkecil");
            System.out.println("");
            
            System.out.println(attention);
            System.out.println("");
            
            Scanner plh2 = new Scanner(System.in);
            System.out.print("Pilih : ");
            Pilih2 = plh2.nextLine();
            
            stmt = conn.createStatement();
            String sql;
            sql = "";
            
            if(Pilih2.equals("")){
                conn.close();
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                SemuaProduk("Masukan Keyword");
            }
            else
                if(Pilih2.equals("1")){
                    sql = "SELECT produk.`Id_produk`, produk.`Nama_produk`, produk.`Harga`, pesanan_produk_retail.`Id_pesan`\n" +
                          "FROM produk\n" +
                          "INNER JOIN pesanan_produk_retail on produk.`Id_produk` = pesanan_produk_retail.`Id_produk`\n" +
                          "WHERE pesanan_produk_retail.`Id_pesan` IS NULL ORDER BY produk.`Harga` DESC;";        
                }
                else
                    if(Pilih2.equals("2")){
                        sql = "SELECT produk.`Id_produk`, produk.`Nama_produk`, produk.`Harga`, pesanan_produk_retail.`Id_pesan`\n" +
                          "FROM produk\n" +
                          "INNER JOIN pesanan_produk_retail on produk.`Id_produk` = pesanan_produk_retail.`Id_produk`\n" +
                          "WHERE pesanan_produk_retail.`Id_pesan` IS NULL ORDER BY produk.`Harga` ASC;";                                                
                    }
                    else
                    {
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                        SemuaProduk("Keyword yang anda masukan salah!");
                    }
            
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("");
            
            //extract data from result set
            int index = 1;
            
            //using linked list to stored data Id_produk
            LinkedList<Integer> Id_list = new LinkedList<Integer>();
            
            while(rs.next()){
                //retrieve column name
                Id_produk      = rs.getInt("Id_produk");
                Nama_produk    = rs.getString("Nama_produk");
                Harga_produk   = rs.getInt("Harga");
                
                Id_list.add(Id_produk);
                
                //display value
                System.out.println("Index = " + index);
                System.out.println("Nama Produk \t = " +Nama_produk);
                System.out.println("Harga \t\t = " +Harga_produk);
                System.out.println("");
                index += 1;
            }
            
            System.out.println("");
            System.out.println("Klik Enter untuk kembali ke menu");
            System.out.print("Ketik nomor untuk melihat barang : ");
            
            //enter checker
            Scanner plh = new Scanner(System.in);
            Pilih = plh.nextLine();
            
            if(Pilih.equals("")){
                conn.close();
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                Pelanggan plg = new Pelanggan();
                plg.CariBarang("");
            }
            else
                if ((Integer.valueOf(Pilih) - 1) < Id_list.size()){
                    conn.close();
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    Produk prd = new Produk();
                    prd.TampilProduk(Id_list.get(Integer.valueOf(Pilih) - 1), "");
                }
                else 
                {
                    System.out.println("");
                    System.out.println("Indeks yang dipilih lebih dari " +Id_list.size() +" barang yang ada!");
                }
                
     
        } catch (SQLException e) {
            System.out.println("Failed " + e.toString());
        }
        catch(ClassNotFoundException e){
            System.out.println("JDBC Driver not found");
        }
    }
}
