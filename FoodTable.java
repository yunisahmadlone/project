package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.*;

import domain.Dishes;
import domain.orders;
import techserv.CustomerDA;


public class FoodTable implements ActionListener {
   private JFrame mainFrame;
   private JLabel headerLabel;
   private JLabel statusLabel;
   private JPanel controlPanel;
   private JButton addBtnOrder;
   public ArrayList<orders> orderList = new ArrayList<orders>();
   
   private CustomerDA custDA = new CustomerDA(); 
   
   
   String[] columnNames = {"RestName", "Distance", "DishPrice", "Rating", "Quantity", "DishName", "orderquantity"};

   
   DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
   // The 0 argument is number rows.

   JTable table = new JTable(tableModel);

   private FoodTable(){
      prepareGUI();
   }
   public static void main(String[] args){
      FoodTable swingControlDemo = new FoodTable();      
      swingControlDemo.showTableDemo();
   }
   private void prepareGUI(){
      mainFrame = new JFrame("Restaurant Food Orders");
      mainFrame.setSize(800,800);
      mainFrame.setLayout(new GridLayout(4, 1));
      
      

      mainFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }        
      });    
      headerLabel = new JLabel("", JLabel.CENTER);        
      statusLabel = new JLabel("",JLabel.CENTER);    
      statusLabel.setSize(10,10);

      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());

      mainFrame.add(headerLabel);
      mainFrame.add(controlPanel);
      mainFrame.add(statusLabel);
      mainFrame.setVisible(true); 
      
      addBtnOrder = new JButton("ADD");  
      addBtnOrder.setFont(new Font("Tahoma", Font.BOLD, 13));  
      addBtnOrder.setBounds(10, 10, 10, 10);  
      addBtnOrder.addActionListener(this);  
      mainFrame.getContentPane().add(addBtnOrder);
      
   // get selected row data From table to textfields 
      table.addMouseListener(new MouseAdapter() {});
      
      
      
      
      
   }
   
   
   
   
   
   
   
   
   private void showTableDemo(){
      headerLabel.setText("Food Orders"); 
      
       
       
      
      
      
      
      
      
      
      
      //Collections.sort(custDA.DishesList); 
      

      double sumtotal = 0.0;
      for (int i = 0; i < custDA.DishesList.size(); i++){
    	   String RestName = custDA.DishesList.get(i).getRestName();
    	   double Distance = custDA.DishesList.get(i).getDistance();
    	   double DishPrice = custDA.DishesList.get(i).getDishPrice();
    	   double Rating = custDA.DishesList.get(i).getRating();
    	   int Quantity = custDA.DishesList.get(i).getQuantity();
    	   String DishName = custDA.DishesList.get(i).getDishName();
    	   int orderquantity = 0;
    	   Object[] data = {RestName, Distance, DishPrice, Rating, Quantity, DishName, orderquantity}; 
    	                               
    	   tableModel.addRow(data);
      
      
      
      
      
      
      JScrollPane scrollPane = new JScrollPane(table);
      scrollPane.setSize(300, 300);
      table.setFillsViewportHeight(true);
      controlPanel.add(scrollPane);      
      mainFrame.setVisible(true);     
   } 
}
   
   

@Override
public void actionPerformed(ActionEvent arg0) {
	
	String ac = arg0.getActionCommand();
	table.revalidate();
	
	int col = 0;
	
    
    switch(ac) {  
    
    case "ADD":
    	custDA.GetMaxorderid();
  	    int ordid = custDA.MaxId;
  	    int nextordid = ordid + 1;
    	for( int row = 0; row < table.getRowCount(); ++row )
        {
    		System.out.println("inside *********************" ); 
            
        	  Dishes Dish;
        	  
        	  
        	  Dish = new Dishes((String)table.getValueAt( row, col ),  
        			  (double)table.getValueAt( row, col+1 ),  
        			  (double)table.getValueAt( row, col+2 ),  
        			  (double)table.getValueAt( row, col+3 ),
        			  Integer.parseInt(table.getValueAt( row, col+4 ).toString()),
        			  table.getValueAt( row, col+5 ).toString(), Integer.parseInt(table.getValueAt( row, col+6 ).toString()));
        	  orders ord = new orders(nextordid, 
			              Dish.getRestName(),
			              Dish.getDishName(),
			              Dish.getDishPrice(),  
			              Dish.getOrderQuantity()
			              );
        	  
        	  System.out.println("dish order quantity each time" ); 
              System.out.println(Dish.getOrderQuantity());
        	  
        	  
        	  
              if (Integer.parseInt(table.getValueAt( row, col+6 ).toString()) != 0) {
					orderList.add(ord);
              		};
        	  
        	custDA.populateOrderList();
        	System.out.println("after commit" ); 
            System.out.println(nextordid );
            
            System.out.println("after commit orderList size" ); 
            System.out.println(orderList.size());
          }
    }
    	table.addMouseListener(new MouseAdapter(){
            
            @Override
            public void mouseClicked(MouseEvent e){
            	custDA.GetMaxorderid();
          	    int ordid = custDA.MaxId;
          	    int nextordid = ordid + 1;
            	
            	if (e.getButton() == MouseEvent.BUTTON1) {
            	for( int row = 0; row < table.getRowCount(); ++row )
                {
                  
                	  Dishes Dish;
                	  
                	  Dish = new Dishes((String)table.getValueAt( row, col ),  
                			  (double)table.getValueAt( row, col+1 ),  
                			  (double)table.getValueAt( row, col+2 ),  
                			  (double)table.getValueAt( row, col+3 ),  
                			  Integer.parseInt(table.getValueAt( row, col+4 ).toString()),
                			  table.getValueAt( row, col+5 ).toString(), Integer.parseInt(table.getValueAt( row, col+6 ).toString()));
                	  orders ord = new orders(nextordid, 
        			              Dish.getRestName(),
        			              Dish.getDishName(),
        			              Dish.getDishPrice(),  
        			              Dish.getOrderQuantity()
        			              );
                	  if (Integer.parseInt(table.getValueAt( row, col+6 ).toString()) != 0) {
                		  						orderList.add(ord);
                	  };
                };
            	
                custDA.populateOrderList();  
                System.out.println("after commit" ); 
                System.out.println(nextordid );
            	
                System.out.println("after commit orderList size" ); 
                System.out.println(orderList.size());
            	
            	
            	
            }
            }});
    	
    	
    	
    	//mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    	ordertable window = new ordertable(custDA, orderList);  
        window.mainFrame.setVisible(true); 
    	
	
	
	// TODO Auto-generated method stub
	
}  
   

}



