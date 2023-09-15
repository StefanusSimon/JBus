package StefanusSimonJBusRS;


/**
 * Modul 2
 * Stefanus Simon Rilando - 2206830422
 */

public class Price
{
    public double price;
    public double rebate;
    public int discount;
    
    public Price(double price, double rebate){
        this.price = price;
        this.rebate = rebate;
        this.discount = 0;   
    }
    
    public Price(double price, int discount){
        this.price = price;
        this.discount = discount;
    }
    
    public Price(double price){
        this.price = 0;
        this.rebate = 0;
    }
    
    public double getDiscountedPrice(){
    if(this.discount >= 100.0f){
        this.discount = 100;
    } else{
        this.price = ((100.0f - (double)this.discount) * price) / 100;
    }
    return 0;
    }
    
    public double  getRebatedPrice(){
    this.price = this.price - this.rebate;
    if(this.price <= 0){
        return 0;
    } else{
        return this.price;
    }  
    }
}



