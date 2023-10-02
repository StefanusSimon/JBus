package StefanusSimonJBusRS;
import java.util.Calendar;



/**
 * Modul 4
 * Stefanus Simon Rilando - 2206830422
 */
public class JBus
{
    public static void main(String[] args){
    Bus testBus = createBus();
     Payment testPayment = new Payment(1, 1, 1, testBus.id, "S1");
     System.out.println(testPayment.getDepartureInfo());
     System.out.println(testPayment.getTime());
     Calendar sched1 = Calendar.getInstance();
     testBus.addSchedule(sched1);
     Calendar sched2 =  Calendar.getInstance();
     sched2.add(Calendar.DAY_OF_MONTH, 3);
     testBus.addSchedule(sched2);
     
     for(Schedule s : testBus.schedules) {
         testBus.printSchedule(s);
     }
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
    
    
