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
                    jmkc("");
                }
    }
    
    //kode jmkc  = Jam Tangan, Kacamata dan Pakaian
    public void jmkc(String attention) throws IOException, InterruptedException{
        
    }
}
