package com.StefanusSimonJBusRS;
import com.StefanusSimonJBusRS.controller.BusController;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * Modul 4
 * Stefanus Simon Rilando - 2206830422
 */
public class Payment extends Invoice
{
    private int busId;
    public Timestamp departureDate;
    public List<String> busSeats;
    
    public Payment(int buyerId, int renterId, int busId, List<String> busSeats, Timestamp departureDate){
        super(buyerId, renterId);
        this.busId = busId;
        this.busSeats = busSeats;
        //format
        this.departureDate = departureDate;
    }
    
    public Payment(Account buyer, Renter renter, int busId, List<String> busSeats, Timestamp departureDate){
        super(buyer, renter);
        this.busId = busId;
        this.busSeats = busSeats;
        this.departureDate = departureDate;
    }
    
    public int getBusId(){
        return busId;
    }
    
    public String getTime(){
        SimpleDateFormat formatted = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        String formattedDate = formatted.format(super.time.getTime());
        return formattedDate;
    } 
    
    public String getDepartureInfo(){
        SimpleDateFormat formatted = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        String formattedDate = formatted.format(super.time.getTime());
        String println = "Payment Info: " + " | Buyer ID: " + buyerId + " | Renter ID: " + renterId + " | Bus ID: " + String.valueOf(busId) +" | Departure Date: " + formattedDate + " | Bus Seat: " + busSeats;
        return println;
    }  
    
    /*public static boolean isAvailable(Timestamp departureSchedule, String seat, Bus bus){
        for(Schedule avail : bus.schedules){
            if(avail.departureSchedule.equals(departureSchedule)){
               for(String seatName : avail.seatAvailability.keySet()){
                   if(seatName.equals(seat) && avail.seatAvailability.get(seat)){
                       avail.bookSeat(seat);
                       return true;
                   }
               }
            }
        }
        return false;
    }*/

    public static Schedule avaialableSchedule (Timestamp departureSchedule, String seat, Bus bus){
        for(Schedule x : bus.schedules){
            if(x.departureSchedule.equals(departureSchedule)){
                if(x.isSeatAvailable(seat)){
                    return x;
                }
            }
        }
        return null;
    }


    public static boolean makeBooking(Timestamp departureSchedule, List<String> seats, Bus bus){
        System.out.println(departureSchedule);
        for(Schedule s : bus.schedules){
            System.out.println(s.isSeatAvailable(seats) + " " + s.departureSchedule.equals(departureSchedule));
            if (s.isSeatAvailable(seats) && s.departureSchedule.equals(departureSchedule)) {
                s.bookSeat(seats);
                return true;
            }
        }
        return false;
    }

    public static Schedule availableSchedule(Timestamp departureSchedule, List<String> seat, Bus bus){
        int cnt = 0;
        for(Schedule x : bus.schedules){
            for(int i=0; i<seat.size(); i++){
                if (x.departureSchedule.equals(departureSchedule) && x.isSeatAvailable(seat.get(i))){
                    cnt++;
                }
            }
            if(cnt==seat.size()){
                return x;
            }
        }
        return null;
    }

    public Bus getBus(){
        Predicate<Bus> predicate = bus -> bus.id == busId;
        return Algorithm.find(BusController.busTable, predicate);
    }
}
