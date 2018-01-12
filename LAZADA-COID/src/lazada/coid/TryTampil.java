/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lazada.coid;

import java.util.*;
import java.io.IOException;
import java.sql.*; 

/**
 *
 * @author SUYATNA-PC
 */
public class TryTampil {
    //JDBC driver name database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    //nama di localhost nya ubah sesuai nama databases
    static final String DB_URL = "jdbc:mysql://localhost/lazada";
    
    //database credential
    static final String USER = "root";
    static final String PASS = "";
    
    public void TampilData(String attention) throws IOException, InterruptedException{
        String Pilih;
        
        System.out.println("Menu :");
        System.out.println("1. Cari Barang");
        System.out.println("2. Daftar/Masuk Retail");
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
            TampilData(strTemp);
        }
            else
                if(Pilih.equals("1")){
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    Pelanggan plg = new Pelanggan();
                    plg.CariBarang("");
                }
                else {
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    String strTemp = "Masukan salah!";
                    TampilData(strTemp);
                }
    }
}
