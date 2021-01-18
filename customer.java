package domain;

 

public class customer {  
  
     private String custId,  
                      custName,  
                      payTerm,  
                      company,  
                      address;  
  
//     Constructor  
     public customer(String custId, String studName, String payTerm, String address, String company) {  
          this.custId = custId;  
          this.custName = studName;  
          this.payTerm = payTerm;  
          this.address = address;  
          this.company = company;  
     }  
  
//     Setter methods  
     public void setCustId(String custId) {  
          this.custId = custId;  
     }  
  
     public void setCustName(String custName) {  
          this.custName = custName;  
     }  
  
     public void setPayTerm(String payTerm) {  
          this.payTerm = payTerm;  
     }  
  
     public void setCompany(String company) {  
          this.company = company;  
     }  
  
     public void setAddress(String address) {  
          this.address = address;  
     }  
       
//     Getter methods  
     public String getCustId() {  
          return custId;  
     }  
  
     public String getCustName() {  
          return custName;  
     }  
  
     public String getPayTerm() {  
          return payTerm;  
     }  
  
     public String getCompany() {  
          return company;  
     }  
  
     public String getAddress() {  
          return address;  
       
}  
}