package StefanusSimonJBusRS;



/**
 * Modul 1
 * Stefanus Simon Rilando - 2206830422
 */
public class JBus
{
    public static void main(String[] args){
        JBus jBus = new JBus();
        System.out.println("Bus ID: " + jBus.getBusId());
        System.out.println("Bus Name: " + jBus.getBusName());
        System.out.println("Is Discount: " + jBus.isDiscount());
        System.out.println("Discount Percentage (1000, 900): " + jBus.getDiscountPercentage(1000, 900));
        System.out.println("Discounted Price (1000, 10.0f): " + jBus.getDiscountedPrice(1000, 10.0f));
        System.out.println("Original Price (900, 10.0f): " + jBus.getOriginalPrice(900, 10.0f));
        System.out.println("Admin Fee Percentage: " + jBus.getAdminFeePercentage());
        System.out.println("Admin Fee (1000): " + jBus.getAdminFee(1000));
        System.out.println("Total Price (10000, 2): " + jBus.getTotalPrice(10000, 2));
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
    
}    
    
    
