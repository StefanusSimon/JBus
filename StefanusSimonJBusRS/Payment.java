package StefanusSimonJBusRS;


/**
 * Modul 3
 * Stefanus Simon Rilando - 2206830422
 */
public class Payment extends Invoice
{
    private int busId;
    public String departureDate;
    public String busSeat;
    
    public Payment(int id, int buyerId, int renterId, String time, int busId, String departureDate, String busSeat){
        super(id, buyerId, renterId, time);
        this.busId = busId;
        this.departureDate = departureDate;
        this.busSeat = busSeat;
    }
    
    public Payment(int id, Account buyer, Renter renter, String time, int busId, String departureDate, String busSeat){
       super(id, buyer, renter, time);
       this.busId = busId;
       this.departureDate = departureDate;
       this.busSeat = busSeat;
    }
    
    public String print(){
        String println = "Payment Info: " + " | Bus ID: " + String.valueOf(busId) +" | Departure Date: " + departureDate + " | Bus Seat: " + busSeat;
        return println;
    }
    
    public int getBusId(){
        return busId;
    }
}
