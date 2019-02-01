/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assign03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author c0719943
 */
public class Assign03 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        
        Scanner kb = new Scanner(System.in);
        
        int customerID;
        System.out.println("ENTER A CUSTOMER ID: ");
        customerID = kb.nextInt();
        
        String custm1 = "SELECT NAME FROM CUSTOMER WHERE CUSTOMER_ID = ?";
        
        String custm2 = "SELECT NAME, DISCOUNT_CODE, ZIP, ADDRESSLINE1, ADDRESSLINE2, CITY, STATE, PHONE, FAX, EMAIL, CREDIT_LIMIT FROM CUSTOMER WHERE " 
                + "CUSTOMER_ID = ? ORDER BY NAME ASC";
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            
            String jdbc = ("jdbc:derby://localhost:1527/sample");
            
            Connection conn = DriverManager.getConnection(jdbc, "app", "app");
            
            PreparedStatement pstmtC = conn.prepareStatement(custm1);
            pstmtC.setInt(1, customerID);
            ResultSet rsC = pstmtC.executeQuery();
            
            while(rsC.next()) {
                String name = rsC.getString("NAME");
                PreparedStatement pstmtC2 = conn.prepareStatement(custm2);
                pstmtC2.setString(1, name);
                ResultSet rsC2 = pstmtC2.executeQuery();
                
                while (rsC2.next()) {
                    System.out.printf("%s: %s\n",
                            rsC2.getString("NAME"),
                            rsC2.getString("DISCOUNT_CODE"),
                            rsC2.getString("ZIP"),
                            rsC2.getString("ADDRESSLINE1"),
                            rsC2.getString("ADDRESSLINE2"),
                            rsC2.getString("CITY"),
                            rsC2.getString("STATE"),
                            rsC2.getString("PHONE"),
                            rsC2.getString("FAX"),
                            rsC2.getString("EMAIL"),
                            rsC2.getString("CREDIT_LIMIT")
                    );
                }
            }  
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Assign03.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int productID;
        System.out.println("ENTER A PRODUCT ID: ");
        productID = kb.nextInt();
        
        String prod1 = "SELECT PRODUCT_ID FROM CUSTOMER WHERE PRODUCT_ID = ?";
        
        String prod2 = "SELECT PRODUCT_ID, MANUFATURER_ID, PRODUCT_CODE, PURCHASE_COST, QUANTITY_ON_HAND, MARKUP, AVAILABLE, DESCRIPTION FROM PRODUCT WHERE " 
                + "PRODUCT_ID = ? ORDER BY PRODUCT_ID ASC";
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            
            String jdbc2 = ("jdbc:derby://localhost:1527/sample");
            
            Connection conn = DriverManager.getConnection(jdbc2, "app", "app");
            
            PreparedStatement pstmtP = conn.prepareStatement(prod1);
            pstmtP.setInt(1, productID);
            ResultSet rsP = pstmtP.executeQuery();
            
            while(rsP.next()) {
                int id = rsP.getInt("PRODUCT_ID");
                PreparedStatement pstmtP2 = conn.prepareStatement(prod2);
                pstmtP2.setInt(1, id);
                ResultSet rsP2 = pstmtP2.executeQuery();
                
                while (rsP2.next()) {
                    System.out.printf("%s: %s\n",
                            rsP2.getString("PRODUCT_ID"),
                            rsP2.getString("MANUFATURER_ID"),
                            rsP2.getString("PRODUCT_CODE"),
                            rsP2.getString("PURCHASE_COST"),
                            rsP2.getString("QUANTITY_ON_HAND"),
                            rsP2.getString("MARKUP"),
                            rsP2.getString("AVAILABLE"),
                            rsP2.getString("DESCRIPTION")
                    );
                }
            }  
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Assign03.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        int orderNum;
        System.out.println("ENTER ORDER NUMBER: ");
        orderNum = kb.nextInt();
        
        int quantity;
        System.out.println("ENTER NUMBER OF QUANTITY : ");
        quantity = kb.nextInt();
        
        int shippingCost;
        System.out.println("ENTER COST OF SHIPPING: ");
        shippingCost = kb.nextInt();
        
        int salesDate;
        System.out.println("ENTER SALES DATE : ");
        salesDate = kb.nextInt();
        
        int shippingDate;
        System.out.println("ENTER DATE OF SHIPPING: ");
        shippingDate = kb.nextInt();
        
        String company;
        System.out.println("ENTER FREIGHT COMPANY: ");
        company = kb.nextLine();
        
        String ord1 = "SELECT ORDER_NUM, FROM PURCHASE_ORDER WHERE ORDER_NUM = ?";
        
        String ord2 = "SELECT QUANTITY FROM PURCHASE_ORDER WHERE ORDER_NUM = ?";
        
        String ord3 = "SELECT SHIPPING_COST FROM PURCHASE_ORDER WHERE ORDER_NUM = ?";
        
        String ord4 = "SELECT SALES_DATE FROM PURCHASE_ORDER WHERE ORDER_NUM = ?";
        
        String ord5 = "SELECT SHIPPING_DATE FROM PURCHASE_ORDER WHERE ORDER_NUM = ?";
        
        String ord6 = "SELECT FREIGHT_COMPANY FROM PURCHASE_ORDER WHERE ORDER_NUM = ?";
        
        String ord7 = "SELECT ORDER_NUM, QUANTITY, SHIPPING_COST, SALES_DATE, SHIPPING_DATE, FREIGHT_COMPANY FROM PURCHASE_ORDER WHERE " 
                + "ORDER_NUM = ? ORDER BY ORDER_NUM ASC"; 
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            
            String jdbc3 = ("jdbc:derby://localhost:1527/sample");
            
            Connection conn = DriverManager.getConnection(jdbc3, "app", "app");
            
            PreparedStatement pstmtO = conn.prepareStatement(ord1);
            pstmtO.setInt(1, orderNum);
           // pstmtO.setInt(1, quantity);
           // pstmtO.setInt(1, shippingCost);
           // pstmtO.setInt(1, salesDate);
           // pstmtO.setInt(1, shippingDate);
           // pstmtO.setString(1, company);

            ResultSet rsO = pstmtO.executeQuery();
            
            while(rsO.next()) {
                int on = rsO.getInt("ORDER_NUM");
                PreparedStatement pstmtO2 = conn.prepareStatement(ord7);
                pstmtO2.setInt(1, on);
                ResultSet rsP2 = pstmtO2.executeQuery();
                
                while (rsP2.next()) {
                    System.out.printf("%s: %s\n",
                            rsP2.getString("ORDER_NUM"),
                            rsP2.getString("QUANTITY"),
                            rsP2.getString("SHIPPING_COST"),
                            rsP2.getString("SALES_DATE"),
                            rsP2.getString("SHIPPING_DATE"),
                            rsP2.getString("FREIGHT_COMPANY")
                    );
                }
            }  
            conn.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Assign03.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }
    
}
