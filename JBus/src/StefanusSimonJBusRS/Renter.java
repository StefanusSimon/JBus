package StefanusSimonJBusRS;


/**
 * Modul 3
 * Stefanus Simon Rilando - 2206830422
 */
public class Renter extends Serializable
{
    public String address;
    public String companyName;
    public int phoneNumber;
    
    public Renter(String companyName){
       super();
       this.companyName = companyName;
    }
    
    public Renter(String companyName, String address){
       super();
       this.companyName = companyName;
       this.address = address;
    }
    
    public Renter(String companyName, int phoneNumber){
       super();
       this.companyName = companyName;
       this.phoneNumber = phoneNumber;
    }
    
    public Renter(String companyName, int phoneNumber, String address){
       super();
       this.companyName = companyName;
       this.phoneNumber = phoneNumber;
       this.address = address;
    }
}
