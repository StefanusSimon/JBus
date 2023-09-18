package StefanusSimonJBusRS;


/**
* Modul 2
 * Stefanus Simon Rilando - 2206830422
 */
public class Voucher
{
    public String name;
    private boolean used;
    public double minimum;
    public double cut;
    public int code;
    public Type type;
    
    public Voucher(String name, int code, Type type, double minimum, double cut){
        this.name = name;
        this.code = code;
        this.type = type;
        this.minimum = minimum;
        this.cut = cut;
        this.used = false;
    }
    
    public double apply(Price price){
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
}
