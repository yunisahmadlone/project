package techserv;


import java.sql.*;  
import java.util.ArrayList;  
import javax.swing.JOptionPane;

import domain.customer;
import domain.Dishes;
import domain.orders;
import java.util.Collections;
  
public class CustomerDA {  
  
     public ArrayList<orders> orderList; 
     public ArrayList<Dishes> DishesList;
     private int recordCursor;
     public int MaxId;
       
     private Connection connection;  
//     Database file directory saved directly on the project   
     private final String dbDir = "/home/yunis/Desktop/project/SQLiteStudio/db";  
       
//     Constructor to initialize set of actions  
     public CustomerDA() {                 
//          Set first record to be shown  
          setRecordCursorAtStart();  
  
//          Initialize and set database connection from SampleDB.db  
          setConnection();  
            
//          Populate application with customer data  
          //populateOrderList();
          
//        Set first record to be shown  
          setRecordCursorAtStart();  

//        Initialize and set database connection from SampleDB.db  
          setConnection();  
          populateDishesList();
     }  
  
     public void setRecordCursorAtStart() {  
          recordCursor = 0;  
     }  
       
//     States the database connection for SQLite that include the recently added SQLite JDBC   
     private void setConnection() {  
          try {  
//               
        	  
        	  
        	  
        	  
        	    
               Class.forName("org.sqlite.JDBC");  
                 
//               Get SQLite connection from given database  
               connection = DriverManager.getConnection("jdbc:sqlite:" + dbDir);  
                 
          } catch (ClassNotFoundException | SQLException e) {  
               // TODO Auto-generated catch block  
               e.printStackTrace();  
          }  
            
     }  
       
//     Retrieve customer record list from database to customerList variable  
     public void populateOrderList() {  
    	 setRecordCursorAtStart();  
    	  
//       Initialize and set database connection from SampleDB.db  
         setConnection();  
    	 
    	 orderList = new ArrayList<orders>();
          setRecordCursorAtStart();
          System.out.print(MaxId);
          System.out.println("MaxId in DA" ); 
          System.out.println(MaxId );
          try {
        	   
               PreparedStatement ps = connection.prepareStatement("SELECT * FROM orders WHERE orderId = '%s'", MaxId-1);  
               ResultSet rs = ps.executeQuery();  
                 
               orders ord;  
               while(rs.next()) {  
                      
//                    Filter records that are stamped inactive or considered deleted  
                    if(rs.getString("stamp").contains("inactive"))  
                         continue;  
                      
                    ord = new orders(rs.getInt("OrderId"),  
                                               rs.getString("RestName"),
                                               rs.getString("DishName"),
                                               rs.getDouble("DishPrice"),  
                                               rs.getInt("quantity")  
                                               );  
                      
                    orderList.add(ord);
                    
                	
                    
                    
                    
               } 
               
                 
          } catch (SQLException e) {  
               // TODO Auto-generated catch block  
               e.printStackTrace();  
          }  
     } 
     
     public void GetMaxorderid() {  
    	 
    	 setRecordCursorAtStart();  
    	  
//       Initialize and set database connection from SampleDB.db  
         setConnection();   
         try {
       	   
              PreparedStatement ps = connection.prepareStatement("SELECT MAX(orderId) FROM orders");  
              ResultSet rs = ps.executeQuery();  
              
              while (rs.next())
                  MaxId = rs.getInt(1);
              
                
         } catch (SQLException e) {  
              // TODO Auto-generated catch block  
              e.printStackTrace(); 
         
         }  
         
    } 
     
     
     
     
     
     
     
//   Retrieve customer record list from database to customerList variable  
   private void populateDishesList() {  
        DishesList = new ArrayList<Dishes>();  
        setRecordCursorAtStart(); 
        try {                 
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM RestaurantsandDishes");  
             ResultSet rs = ps.executeQuery();  
               
             Dishes Dish;  
             while(rs.next()) {  
                    
//                  Filter records that are stamped inactive or considered deleted  
                  if(rs.getString("stamp").contains("inactive"))  
                       continue;  
                    
                  Dish = new Dishes(rs.getString("RestName"),  
                                             rs.getDouble("Distance"),  
                                             rs.getDouble("DishPrice"),
                                             rs.getDouble("Rating"), 
                                             rs.getInt("Quantity"),
                                             rs.getString("DishName"),
                                             0);  
                    
                  DishesList.add(Dish);  
             } 
             
               
        } catch (SQLException e) {  
             // TODO Auto-generated catch block  
             e.printStackTrace();  
        }  
   }  
     
     
     
     
     
     
     
     
       
//     This method is usable to pass query to be committed and update the database  
     private void commitStatement(String query) {  
          try {       
//               Prepare string query as statement to execute       
               PreparedStatement ps = connection.prepareStatement(query);  
                 
//               Execute statement for database to update  
               ps.executeUpdate();  
                 
          } catch (SQLException e) {  
//               Inform that exception occurred from executing statement  
               JOptionPane.showMessageDialog(null, "An error occured due invalid/conflict in customer data inputted.", "", JOptionPane.ERROR_MESSAGE);  
               e.printStackTrace();  
          }            
            
//          Update customerList variable  
          populateOrderList();  
     }  
  
//     Adds passed customer object to Customer table   
     public void addorder(orders cust) {  
          commitStatement(String.format("INSERT INTO orders VALUES ('%s', '%s', '%s', '%s', '%s', 'active')",  
                                              cust.getorderId(),  
                                              cust.getRestName(),  
                                              cust.getDishPrice(),  
                                              cust.getquantity(),  
                                              cust.getDishName()));  
            
          setRecordCursorAtEnd();  
     }  
       
//     Updates passed old customer info to latest info  
     public void updateorder(int oldCustId, orders cust) {  
          commitStatement(String.format("UPDATE customer SET custId = '%s', name = '%s', payterm = '%s', address = '%s', company = '%s' WHERE custId = '%s'",  
							        		  cust.getorderId(),  
							                  cust.getRestName(),  
							                  cust.getDishPrice(),  
							                  cust.getquantity(),  
							                  cust.getDishName(),  
                                              MaxId));  
     }  
       
//     Deletes customer from database by updating stamp state to inactive  
     public void deleteorder(String custId) {  
          commitStatement(String.format("UPDATE customer SET stamp = 'inactive' WHERE custId = '%s'", custId));  
          setRecordCursorAtStart();  
     }  
  
     public orders getCurrentOrder() {
    	  System.out.println(orderList.size());
          return orderList.get(0);  
     }  
       
     public void setRecordCursorAtEnd() {  
          recordCursor = orderList.size() - 1;  
     }  
       
     public orders getNextOrder() {  
          return orderList.get(  
                    recordCursor = recordCursor >= orderList.size()-1? orderList.size()-1:++recordCursor);   
     }  
       
     public orders getPreviousOrder() {  
          return orderList.get(  
                    recordCursor = recordCursor <= 0? 0:--recordCursor);   
     }  
  
     public orders getFirstOrder() {  
          return orderList.get(recordCursor = 0);   
     }  
       
     public orders getLastOrder() {  
          return orderList.get(recordCursor = orderList.size()-1);   
     }  
       
     public ArrayList<orders> getorderList() {  
          return orderList;  
     }       

} 