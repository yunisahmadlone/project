package domain;

public class Dishes  {  
	  
    public String RestName;
    private double  Distance;
    public double  DishPrice;
    private double    Rating;
    private int    Quantity;
    private String DishName;
    private int OrderQuantity;
                     
 
//    Constructor  
    public Dishes(String RestName, double Distance, double DishPrice, double Rating, int Quantity, String DishName, int OrderQuantity) {  
         this.RestName = RestName;  
         this.Distance = Distance;  
         this.DishPrice = DishPrice;  
         this.Rating = Rating;  
         this.Quantity = Quantity;  
         this.DishName = DishName;
         this.OrderQuantity = OrderQuantity;
    }  
 

    
    
    
      
//    Getter methods  
    public String getRestName() {  
         return RestName;  
    }  
 
    public double getDistance() {  
         return Distance;  
    }  
 
    public double getDishPrice() {  
         return DishPrice;  
    }  
 
    public double getRating() {  
         return Rating;  
    }  
 
    public int getQuantity() {  
         return Quantity; 
    }
      
    public String getDishName() {  
         return DishName;  
}  
    
    public int getOrderQuantity() {  
        return OrderQuantity;  
}  
    public void setOrderQuantity(int OrderQuantity) {  
        this.OrderQuantity = OrderQuantity;  
}




	public double compareTo(Dishes arg0) {
		// TODO Auto-generated method stub
		double compareage=((Dishes)arg0).getDishPrice();
        /* For Ascending order*/
        return this.DishPrice-compareage;
		
		
	}  
}