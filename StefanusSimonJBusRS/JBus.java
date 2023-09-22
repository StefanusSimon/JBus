package StefanusSimonJBusRS;



/**
 * Modul 3
 * Stefanus Simon Rilando - 2206830422
 */
public class JBus
{
    public static void main(String[] args){
        Payment testPayment = new Payment(1, 1, 1, "A", 1, "A", "A");
        Invoice testInvoice = new Invoice(2,2,2, "B");
        Station testStation = new Station(3, "C", City.DEPOK);
        System.out.println(testPayment.print());
        System.out.println(testInvoice.print());
        System.out.println(testStation.print());
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
    
    /*public static Bus createBus(){
        Price price = new Price (750000, 5);
        Bus bus = new Bus ("Netlab Stefanus", Facility.LUNCH, price, 25);
        return bus;
    }*/
    
}    
    
    
