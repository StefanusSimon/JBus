package com.StefanusSimonJBusRS;

import java.sql.Timestamp;



/**
 * Modul 4
 * Stefanus Simon Rilando - 2206830422
 */
public class Invoice extends Serializable
{
    public Timestamp time;
    public int buyerId;
    public int renterId;
    public BusRating rating;
    public PaymentStatus status;
    
    public enum BusRating{
    NONE, NEUTRAL, GOOD, BAD;
    }
    
    public enum PaymentStatus{
    FAILED, WAITING, SUCCESS;
    }

    protected Invoice(int buyerId, int renterId){
        super();
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.time = new Timestamp(System.currentTimeMillis());
        this.rating = BusRating.NONE;
        this.status = PaymentStatus.WAITING;
    }
    
    public Invoice(Account buyer, Renter renter){
        super();
        this.time = new Timestamp(System.currentTimeMillis());
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.rating = BusRating.NONE;
        this.status = PaymentStatus.WAITING;
    }

    
    /*public String toString(){
        String println = "Invoice Info: " + " Id: " + id + "| Time: " + time + " | Buyer Info: " + buyerId + " | Renter Info: " + renterId + " | Status: " + status + " | Rating: " + rating;
        return println;
    }*/
}
