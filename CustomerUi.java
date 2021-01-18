package ui; 




import java.awt.EventQueue;

import javax.swing.JFrame;

  

import java.awt.EventQueue;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import javax.swing.JFrame;  
import javax.swing.JLabel;  
import javax.swing.JOptionPane;  
import javax.swing.JTextField;

import techserv.CustomerDA;
import domain.orders;
import javax.swing.JTable;

import javax.swing.JButton;  
import java.awt.Font;  
import java.awt.Color;  
  
public class CustomerUi {  
  
     public JFrame frame;  
     private JTextField orderIdTF;  
     private JTextField orderNameTF;  
     private JTextField DishPriceTF;  
     private JTextField quantityTF;  
     private JTextField DishNameTF;  
     private JButton nextBtn;  
     private JButton previousBtn;  
     private JButton addBtn;  
     private JButton updateBtn;  
     private JButton deleteBtn;  
     private orderListener orderListener;  
     private JButton firstBtn;  
     private JButton lastBtn;  
     private CustomerDA CustomerDA;  
     private JTable table; 
     
  
     /**  
      * Create the application.  
      */  
     public CustomerUi(JTable table, CustomerDA CustomerDApass) {  
    	  
    	  this.table = table;
    	  CustomerDA = CustomerDApass;  
          orderListener = new orderListener();  
          initialize();  
          updateorderForm();  
            
//          Set text fields to read mode  
          areTextFieldsEditable(false);  
     }  
  
     /**  
      * Initialize the contents of the frame.  
      * Sets all of the components and linked to an action listener that handles the control  
      */  
     private void initialize() {  
          frame = new JFrame();  
          frame.setBounds(100, 100, 570, 300);  
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
          frame.getContentPane().setLayout(null);  
            
          JLabel orderIdLbl = new JLabel("order ID:");  
          orderIdLbl.setBounds(58, 10, 76, 16);  
          frame.getContentPane().add(orderIdLbl);  
            
          orderIdTF = new JTextField();  
          orderIdTF.setBounds(141, 7, 376, 22);  
          frame.getContentPane().add(orderIdTF);  
          orderIdTF.setColumns(10);  
          orderIdTF.setEditable(false);  
            
          JLabel orderNameLbl = new JLabel("order name:");  
          orderNameLbl.setBounds(38, 39, 96, 16);  
          frame.getContentPane().add(orderNameLbl);  
            
          orderNameTF = new JTextField();  
          orderNameTF.setBounds(141, 36, 376, 22);  
          orderNameTF.setColumns(10);  
          frame.getContentPane().add(orderNameTF);  
            
          JLabel payTermLbl = new JLabel("DishPrice:");  
          payTermLbl.setBounds(82, 68, 52, 16);  
          frame.getContentPane().add(payTermLbl);  
            
          DishPriceTF = new JTextField();  
          DishPriceTF.setBounds(141, 65, 376, 22);  
          DishPriceTF.setColumns(10);  
          frame.getContentPane().add(DishPriceTF);  
            
          JLabel addressLbl = new JLabel("quantity:");  
          addressLbl.setBounds(83, 97, 51, 16);  
          frame.getContentPane().add(addressLbl);  
            
          quantityTF = new JTextField();  
          quantityTF.setBounds(141, 94, 376, 22);  
          quantityTF.setColumns(10);  
          frame.getContentPane().add(quantityTF);  
            
          JLabel companyLbl = new JLabel("DishName:");  
          companyLbl.setBounds(76, 126, 58, 16);  
          frame.getContentPane().add(companyLbl);  
            
          DishNameTF = new JTextField();  
          DishNameTF.setBounds(141, 123, 376, 22);  
          DishNameTF.setColumns(10);  
          frame.getContentPane().add(DishNameTF);  
            
          nextBtn = new JButton(">");  
          nextBtn.setForeground(Color.BLUE);  
          nextBtn.setBounds(289, 191, 125, 25);  
          nextBtn.addActionListener(orderListener);  
          frame.getContentPane().add(nextBtn);  
            
          previousBtn = new JButton("<");  
          previousBtn.setForeground(Color.BLUE);  
          previousBtn.setBounds(152, 191, 125, 25);  
          previousBtn.addActionListener(orderListener);  
          frame.getContentPane().add(previousBtn);  
            
          addBtn = new JButton("ORDER");  
          addBtn.setFont(new Font("Tahoma", Font.BOLD, 13));  
          addBtn.setBounds(15, 228, 173, 25);  
          addBtn.addActionListener(orderListener);  
          frame.getContentPane().add(addBtn);  
            
          updateBtn = new JButton("UPDATE");  
          updateBtn.setFont(new Font("Tahoma", Font.BOLD, 13));  
          updateBtn.setBounds(200, 228, 166, 25);  
          updateBtn.addActionListener(orderListener);  
          frame.getContentPane().add(updateBtn);  
            
          deleteBtn = new JButton("DELETE");  
          deleteBtn.setFont(new Font("Tahoma", Font.BOLD, 13));  
          deleteBtn.setBounds(378, 228, 173, 25);  
          deleteBtn.addActionListener(orderListener);  
          frame.getContentPane().add(deleteBtn);  
            
          firstBtn = new JButton("<<");  
          firstBtn.addActionListener(orderListener);  
          firstBtn.setForeground(Color.BLUE);  
          firstBtn.setBounds(15, 191, 125, 25);  
          frame.getContentPane().add(firstBtn);  
            
          lastBtn = new JButton(">>");  
          lastBtn.addActionListener(orderListener);  
          lastBtn.setForeground(Color.BLUE);  
          lastBtn.setBounds(426, 191, 125, 25);  
            
          frame.getContentPane().add(lastBtn);            
          frame.setLocationRelativeTo(null);  
          frame.setResizable(false);  
          frame.setTitle("order Management");  
     }  
       
//     Updates form fields with current order navigated  
     private void updateorderForm() {  
          orders order = CustomerDA.getCurrentOrder();  
            
          orderIdTF.setText(String.valueOf(order.getorderId()));  
          orderNameTF.setText(order.getRestName());  
          DishPriceTF.setText(String.valueOf(order.getDishPrice()));  
          quantityTF.setText(String.valueOf(order.getquantity()));  
          DishNameTF.setText(order.getDishName());  
     }  
       
//     Sets form fields to read or edit mode  
     private void areTextFieldsEditable(boolean flag) {  
          orderIdTF.setEditable(flag);  
          orderNameTF.setEditable(flag);  
          DishPriceTF.setEditable(flag);  
          quantityTF.setEditable(flag);  
          DishNameTF.setEditable(flag);  
     }  
       
//     An action listener class that uses the methods from CustomerDA to give actions  
//     in the components when users interact with it  
     class orderListener implements ActionListener {  
  
          @Override  
          public void actionPerformed(ActionEvent ev) {  
               String ac = ev.getActionCommand();  
                 
               switch(ac) {  
               case ">": nextorder(); break;  
               case "<": prevorder(); break;  
               case ">>": lastorder(); break;  
               case "<<": firstorder(); break;  
               case "ORDER": setEditMode("ORDER"); break;  
               case "UPDATE": setEditMode("UPDATE"); break;  
               case "SAVE ADD": addorder();   
                                    resetEditMode(); break;  
               case "SAVE UPDATE": updateorder();   
                                        resetEditMode(); break;  
               case "CANCEL": resetEditMode(); break;  
               case "DELETE": deleteorder(); break;  
               }  
          }  
  
//          Resets form to read mode  
          private void resetEditMode() {  
               nextBtn.setText(">");  
               previousBtn.setText("<");  
                 
               areTextFieldsEditable(false);  
               addBtn.setEnabled(true);  
               updateBtn.setEnabled(true);  
               deleteBtn.setEnabled(true);  
               firstBtn.setEnabled(true);  
               lastBtn.setEnabled(true);  
                 
               updateorderForm();  
          }  
            
//          Utilizes form to add or update mode  
          private void setEditMode(String mode) {                 
               areTextFieldsEditable(true);  
                 
               if(mode.equals("ORDER")) clearForm();  
               else if(mode.equals("UPDATE")) orderIdTF.setEditable(false);  
                      
                 
               nextBtn.setText("CANCEL");  
               previousBtn.setText("SAVE " + mode);  
                 
               firstBtn.setEnabled(false);  
               lastBtn.setEnabled(false);  
               addBtn.setEnabled(false);  
               addBtn.setEnabled(false);  
               updateBtn.setEnabled(false);  
               deleteBtn.setEnabled(false);  
          }  
            
          private void clearForm() {  
               orderIdTF.setText("");  
               orderNameTF.setText("");  
               DishPriceTF.setText("");  
               quantityTF.setText("");  
               DishNameTF.setText("");  
          }  
  
//          Confirmation to delete selected order  
          private void deleteorder() {  
               int res = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete order?", "",   
                         JOptionPane.YES_NO_OPTION);  
                 
               if(res == 0) {  
                    CustomerDA.deleteorder(orderIdTF.getText());  
                    updateorderForm();  
               }  
          }  
  
//          Update order record to database from form fields  
          private void updateorder() {  
               CustomerDA.updateorder(Integer.parseInt(orderIdTF.getText()),  
            		   new orders(  
                    		   Integer.parseInt(orderIdTF.getText()), 
                                                orderNameTF.getText(), 
                                                DishNameTF.getText(),
                                                Double.parseDouble(DishPriceTF.getText()),  
                                                Integer.parseInt(quantityTF.getText()) 
                                                  
                                                ));  
               updateorderForm();  
          }  
  
//          Add order record to database from form fields  
          private void addorder() {  
               CustomerDA.addorder(     new orders(  
            		   Integer.parseInt(orderIdTF.getText()), 
                                        orderNameTF.getText(), 
                                        DishNameTF.getText(),
                                        Double.parseDouble(DishPriceTF.getText()),  
                                        Integer.parseInt(quantityTF.getText()) 
                                          
                                        ));  
               updateorderForm();  
          }  
  
//          Navigate to previous order record  
          private void prevorder() {  
               CustomerDA.getPreviousOrder();  
               updateorderForm();  
          }  
  
//          Navigate to next order record  
          private void nextorder() {  
               CustomerDA.getNextOrder();  
               updateorderForm();  
          }  
            
//          Navigate to first order record  
          private void firstorder() {  
               CustomerDA.getFirstOrder();  
               updateorderForm();  
          }  
            
//          Navigate to last order record  
          private void lastorder() {  
               CustomerDA.getLastOrder();  
               updateorderForm();  
          }  
            
     } 
}  