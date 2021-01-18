package domain;

 

public class orders {  
  
     public int orderId;  
     public String RestName,  
                      DishName; 
     public double   DishPrice;
     public int      quantity;  
  
//     Constructor  
     public orders(int orderId, String RestName, String DishName, double DishPrice, int quantity) {  
          this.orderId = orderId;  
          this.RestName = RestName;  
          this.DishPrice = DishPrice;  
          this.quantity = quantity;
          this.DishName = DishName;
           
     }  
  
//     Setter methods  
     public void setorderId(int orderId) {  
          this.orderId = orderId;  
     }  
  
     public void setRestName(String RestName) {  
          this.RestName = RestName;  
     }  
  
     public void setDishName(String RestName) {  
         this.DishName = RestName;  
    } 
     
     
     public void setDishPrice(double DishPrice) {  
          this.DishPrice = DishPrice;  
     }  
  
     
     public void setquantity(int quantity) {  
          this.quantity = quantity;  
     }  
       
//     Getter methods  
     public int getorderId() {  
          return orderId;  
     }  
  
     public String getRestName() {  
          return RestName;  
     }  
  
     public String getDishName() {  
         return DishName;  
    }  
     
     
     
     public double getDishPrice() {  
          return DishPrice;  
     }  
  
     
     public int getquantity() {  
          return quantity;  
       
}  
}