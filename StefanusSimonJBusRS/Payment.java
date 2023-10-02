package StefanusSimonJBusRS;
import java.util.Calendar;
import java.text.SimpleDateFormat;


/**
 * Modul 4
 * Stefanus Simon Rilando - 2206830422
 */
public class Payment extends Invoice
{
    private int busId;
    public Calendar departureDate;
    public String busSeat;
    
    public Payment(int id, int buyerId, int renterId, int busId,String busSeat){
        super(id, buyerId, renterId);
        this.busId = busId;
        this.busSeat = busSeat;
        this.departureDate = Calendar.getInstance();
        departureDate.add(Calendar.DAY_OF_MONTH,2);
    }
    
    public Payment(int id, Account buyer, Renter renter, int busId, String busSeat){
        super(id, buyer, renter);
        this.busId = busId;
        this.busSeat = busSeat;
        this.departureDate = Calendar.getInstance();
        departureDate.add(Calendar.DAY_OF_MONTH,2);
    }
    
    public int getBusId(){
        return busId;
    }
    
    public String getTime(){
        SimpleDateFormat formatted = new SimpleDateFormat ("MMMM, d yyy hh:mm:ss");
        String formattedDate = formatted.format(super.time.getTime());
        return formattedDate;
    } 
    
    public String getDepartureInfo(){
        SimpleDateFormat formatted = new SimpleDateFormat ("MMMM, d yyy hh:mm:ss");
        String formattedDate = formatted.format(super.time.getTime());
        String println = "Payment Info: " + " | Buyer ID: " + buyerId + " | Renter ID: " + renterId + " | Bus ID: " + String.valueOf(busId) +" | Departure Date: " + formattedDate + " | Bus Seat: " + busSeat;
        return println;
    }   
}
