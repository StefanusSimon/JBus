package StefanusSimonJBusRS;
import java.util.Calendar;
import java.sql.Timestamp;


/**
 * Modul 4
 * Stefanus Simon Rilando - 2206830422
 */
public class JBus
{
    public static void main(String[] args){
    Bus b = createBus();
     
     Timestamp schedule1 = Timestamp.valueOf("2023-7-18 15:00:00");
     Timestamp schedule2 = Timestamp.valueOf("2023-7-20 12:00:00");
     
     b.addSchedule(schedule1);
     b.addSchedule(schedule2);
     
     b.schedules.forEach(Schedule::printSchedule);
     
     // Invalid date
     Timestamp t1 = Timestamp.valueOf("2023-7-19 15:00:00");
     System.out.println("Invalid date, t1 : " + String.valueOf(Payment.makeBooking(t1, "RS01", b)));
     
     // Valid date, invalid seat
     Timestamp t2 = Timestamp.valueOf("2023-7-18 15:00:00");
     System.out.println("Valid date, invalid seat, t2 : " + String.valueOf(Payment.makeBooking(t2, "RS20", b)));
     
     // Valid date, valid seat
     System.out.println("Valid date, valid seat, t2 : " + String.valueOf(Payment.makeBooking(t2, "RS07", b)));
     
     Timestamp t3 = Timestamp.valueOf("2023-7-20 12:00:00");
     System.out.println("Valid date, valid seat, t3 : " + String.valueOf(Payment.makeBooking(t3, "RS01", b)));
     System.out.println("Valid date, valid seat, t3 again : " + String.valueOf(Payment.makeBooking(t3, "RS01", b)));
     
     System.out.println("\nOH MY BUSS BUSS BUZZZ");
     System.out.println("");
     
     b.schedules.forEach(Schedule::printSchedule);
    }
    
    public static int getBusId(){
    return 0;
    }
    
    public static String getBusName(){
    return "Bus";
    }
    
    public static boolean isDiscount(){
    return true;
    }
    
    public static float getDiscountPercentage(int beforeDiscount, int afterDiscount){
        float discPercent;
        if (afterDiscount >= beforeDiscount){
         discPercent = 0.0f; 
        } else {
            discPercent = (float)((beforeDiscount - afterDiscount) / (float)(beforeDiscount)) * 100;
        }
    return discPercent;
    }
    
    public static int getDiscountedPrice(int price, float discountPercentage){
        int discPrice = 0;
        if (discountPercentage > 100.0f){
            discountPercentage = 100.0f;
        } else {
            discPrice = (int)((100.0f - discountPercentage) * price) / 100;
        }
    return discPrice;
    }
    
    public static int getOriginalPrice(int discountedPrice, float discountPercentage){
        return  (int)((100 * discountedPrice) / (100.0f - discountPercentage));
    }
    
    public static float getAdminFeePercentage(){
    return 0.05f;
    }
    
    public static int getAdminFee(int price){
        float adminFeePercentage = getAdminFeePercentage();
        return (int)(price * adminFeePercentage);
    }
    
    public static int getTotalPrice(int price, int numberOfSeat){
    return (int) ((price * numberOfSeat) + getAdminFee(price * numberOfSeat));
    }
    
    public static Bus createBus() {
        Price price = new Price(750000, 5);
        Bus bus = new Bus(1, "Netlab Bus", Facility.LUNCH, price, 25, BusType.REGULER, City.BANDUNG, new Station(1, "Depok Terminal", City.DEPOK, "Jl. Margonda Raya"), new Station(2, "Halte UI", City.JAKARTA, "Universitas Indonesia"));
        return bus;
    }
    
}    
    
    
