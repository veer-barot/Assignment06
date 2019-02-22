/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author c0719943
 */
public class DBUtils {
    
    public final static String SALT = "THISISArandomSTRINGofCHARACTERSusedTOsaltTHEpasswords"; 
    
    public static String hash(String password) {
        
        try {
            String salted = password + SALT;
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] hash = md.digest(salted.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(b & 0xff).toUpperCase();
                if (hex.length() == 1) {
                    sb.append(0);
                }
                sb.append(hex);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException |UnsupportedEncodingException ex) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static Connection getConnection() throws SQLException  {
        //Connection conn = null;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");           
           
        } catch (ClassNotFoundException  ex){
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String hostname = "localhost";
        String port = "1527";
        String dbname = "blog";
        String username = "blog";
        String password = "blog";
        String jdbc = String.format("jdbc:derby://%s:%s/%s", hostname, port, dbname );
        return DriverManager.getConnection(jdbc, username, password);
    }
}
