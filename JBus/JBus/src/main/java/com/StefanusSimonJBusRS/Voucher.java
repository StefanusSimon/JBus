package com.StefanusSimonJBusRS;


import com.StefanusSimonJBusRS.dbjson.Serializable;

/**
* Modul 4
* Stefanus Simon Rilando - 2206830422
*/
public class Voucher extends Serializable
{
    
    private boolean used;
    public double cut;
    public Type type;
    public double minimum;
    public int code;
    public String name;
    
    public Voucher(String name, int code, Type type, double minimum, double cut){
        super();
        this.name = name;
        this.code = code;
        this.type = type;
        this.minimum = minimum;
        this.cut = cut;
        this.used = false;
    }
    
    public double apply(Price price){
        this.used = true;
        if(this.type == Type.REBATE){
            if(cut > price.price){
                return 0;
            }
            return price.price - cut;
        } else {
            if(this.cut >= 100.0){
                this.cut = 100;
            } 
                return ((100.0 - this.cut) * price.price) / 100.0;
        }
    }
    
    public boolean isUsed(){
        return this.used;
    }
    
    public boolean canApply(Price price){
        if(price.price > this.minimum && !this.used){
            return true;
        } else {
            return false;
        }
    }
    
     public Object write(){
       return null;
   }
    
    public Boolean read(String obj){
        return false;
    }
}
