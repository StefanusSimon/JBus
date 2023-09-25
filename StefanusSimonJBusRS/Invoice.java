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
    public BusRating rating;
    public PaymentStatus status;
    
    protected Invoice(int id, int buyerId, int renterId, String time, BusRating rating, PaymentStatus status){  
        super(id);
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.time = time;
        this.rating = BusRating.NONE;
        this.status = PaymentStatus.WAITING;
    }
    
    public Invoice(int id, Account buyer, Renter renter, String time, BusRating rating, PaymentStatus status){
        super(id);
        this.time = time;
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.rating = BusRating.NONE;
        this.status = PaymentStatus.WAITING;
    }
    
    public String toString(){
        String println = "Invoice Info: " + " Id: " + id + "| Time: " + time + " | Buyer Info: " + buyerId + " | Renter Info: " + renterId + " | Status: " + status + " | Rating: " + rating;
        return println;
    }
}
