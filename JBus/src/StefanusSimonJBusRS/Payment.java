package StefanusSimonJBusRS;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;


/**
 * Modul 4
 * Stefanus Simon Rilando - 2206830422
 */
public class Payment extends Invoice
{
    private int busId;
    public Timestamp departureDate;
    public String busSeat;
    
    public Payment(int buyerId, int renterId, int busId,String busSeat, Timestamp departureDate){
        super(buyerId, renterId);
        this.busId = busId;
        this.busSeat = busSeat;
        this.departureDate = new Timestamp(System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 2));
    }
    
    public Payment(Account buyer, Renter renter, int busId, String busSeat, Timestamp departureDate){
        super(buyer, renter);
        this.busId = busId;
        this.busSeat = busSeat;
        this.departureDate = new Timestamp(System.currentTimeMillis()+ (1000 * 60 * 60 * 24 * 2));
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
    
    /*public static boolean isAvailable(Timestamp departureSchedule, String seat, Bus bus){
        for(Schedule avail : bus.schedules){
            if(avail.departureSchedule.equals(departureSchedule)){
               for(String seatName : avail.seatAvailability.keySet()){
                   if(seatName.equals(seat) && avail.seatAvailability.get(seat)){
                       avail.bookSeat(seat);
                       return true;
                   }
               }
            }
        }
        return false;
    }*/
    
    public static boolean makeBooking(Timestamp departureSchedule, String seat, Bus bus){
      for(Schedule avail : bus.schedules){
            if(avail.departureSchedule.equals(departureSchedule)){
               for(String seatName : avail.seatAvailability.keySet()){
                   if(seatName.equals(seat) && avail.seatAvailability.get(seat)){
                       avail.bookSeat(seat);
                       return true;
                   }
               }
            }
        }
        return false;
    }

}
