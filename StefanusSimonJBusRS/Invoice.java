package StefanusSimonJBusRS;


/**
 * Modul 3
 * Stefanus Simon Rilando - 2206830422
 */
public class Invoice extends Serializable
{
    public String time;
    public int buyerId;
    public int renterId;
    
    protected Invoice(int id, int buyerId, int renterId, String time){  
        super(id);
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.time = time;
    }
    
    public Invoice(int id, Account buyer, Renter renter, String time){
        super(id);
        this.time = time;
        this.buyerId = buyerId;
        this.renterId = renterId;
    }
    
    public String print(){
        String println = "Invoice Info: " + " | Time: " + time + " | Buyer Info: " + buyerId + " | Renter Info: " + renterId;
        return println;
    }
}
