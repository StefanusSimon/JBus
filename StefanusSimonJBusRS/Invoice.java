package StefanusSimonJBusRS;
import java.util.Calendar;


/**
 * Modul 4
 * Stefanus Simon Rilando - 2206830422
 */
public class Invoice extends Serializable
{
    public Calendar time;
    public int buyerId;
    public int renterId;
    public BusRating rating;
    public PaymentStatus status;
    
    protected Invoice(int id, int buyerId, int renterId){  
        super(id);
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.time = Calendar.getInstance();
        this.rating = BusRating.NONE;
        this.status = PaymentStatus.WAITING;
    }
    
    public Invoice(int id, Account buyer, Renter renter){
        super(id);
        this.time = Calendar.getInstance();
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
