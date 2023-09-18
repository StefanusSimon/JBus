package StefanusSimonJBusRS;

/**
 * Modul 2
 * Stefanus Simon Rilando - 2206830422
 */

public class Price
{
    public double price;
    public double rebate;
    /**public int discount*/;
    
    public Price(double price, double rebate){
        this.price = price;
        this.rebate = rebate;   
    }
    
    /**public Price(double price, int discount){
        this.price = price;
        this.discount = discount;
    }*/
    
    public Price(double price){
        this.price = price;
        this.rebate = 0;
    }
    
    /**private double getDiscountedPrice(){
    /**if(this.discount >= 100.0f){
        this.discount = 100;
    } else{
        this.price = ((100.0f - (double)this.discount) * price) / 100;
    }
    return 0;
    }*/
    
    /**private double  getRebatedPrice(){
    if(rebate > price){
        return 0;
    } 
    return price - rebate;
    }*/
}



