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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

import domain.Dishes;
import domain.orders;
import techserv.CustomerDA;


public class ordertable implements ActionListener {
   public JFrame mainFrame;
   private JLabel headerLabel;
   private JLabel statusLabel;
   private JPanel controlPanel;
   private JLabel addBtnOrder;
   private infoBox info;
   public ArrayList<orders> orderList;
   private double sumtotal = 0.0;
   private CustomerDA custDA;
   
   
   String[] columnNames = {"orderId", "RestName", "DishPrice", "Quantity", "DishName", "orderquantity"};

   
   DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
   // The 0 argument is number rows.

   JTable table = new JTable(tableModel);

   public ordertable(CustomerDA custDA, ArrayList<orders> orderList){
	   this.custDA = custDA;
	   this.orderList = orderList;
       prepareGUI();
       showTableDemo();
   }
   
   private void prepareGUI(){
	  mainFrame = new JFrame("Food Orders");
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
      
      addBtnOrder = new JLabel("Summary");  
      addBtnOrder.setFont(new Font("Tahoma", Font.BOLD, 13));  
      addBtnOrder.setBounds(10, 10, 10, 10);  
       
      mainFrame.getContentPane().add(addBtnOrder);
      
   // get selected row data From table to textfields 
      table.addMouseListener(new MouseAdapter() {});
      

      
      
      
      
      
   }
   
   
   
   
   
   
   
   
   private void showTableDemo(){
      headerLabel.setText("Food Orders and Summary"); 
      
       
      System.out.println("before creating jtable new" ); 
      System.out.println(this.orderList.size() );
      
      
      
      
      
      
      
      
      
      

      
      for (int i = 0; i < this.orderList.size(); i++){
    	   int orderId = this.orderList.get(i).getorderId();
    	   String RestName = this.orderList.get(i).getRestName();
    	   
    	   double DishPrice = this.orderList.get(i).getDishPrice();
    	   
    	   int Quantity = this.orderList.get(i).getquantity();
    	   String DishName = this.orderList.get(i).getDishName();
    	   int orderquantity = 0;
    	   Object[] data = {orderId, RestName, DishPrice, Quantity, DishName, Quantity}; 
    	   sumtotal = sumtotal + Quantity*DishPrice;                           
    	   tableModel.addRow(data);
      
      
      }
      
      
      
      
      System.out.println("order list size" ); 
      System.out.println(custDA.orderList.size() );      
      JScrollPane scrollPane = new JScrollPane(table);
      scrollPane.setSize(300, 300);
      table.setFillsViewportHeight(true);
      controlPanel.add(scrollPane);      
      mainFrame.setVisible(true);     
   } 

   

   public class infoBox
   {

       public void infoBox(int infoMessage, String titleBar)
       {
           JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
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
        	  if (Dish.getOrderQuantity() > 0) {
        		  						custDA.addorder(ord);
        		  						
        	  };
        	  
        	custDA.populateOrderList();  
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
                	  if (Dish.getOrderQuantity() > 0) {
                		  						custDA.addorder(ord);
                	  };
                };
            	
            	
            	
            	
            	
            	
            	
            	
            }
            }});
    	
    	
    	
    	mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    	//CustomerUi window = new CustomerUi(table, custDA);  
        //window.frame.setVisible(true); 
    	
	
	
	// TODO Auto-generated method stub
	
}  
   

}



