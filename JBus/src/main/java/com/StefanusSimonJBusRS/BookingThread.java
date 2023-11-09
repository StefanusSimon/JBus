package com.StefanusSimonJBusRS;
import java.sql.Timestamp;
import java.util.Collections;

public class BookingThread extends Thread {
    private Bus bus;
    private Timestamp timestamp;
    public BookingThread(String name, Bus bus, Timestamp timestamp){
        super(name);
        this.bus = bus;
        this.timestamp = timestamp;
        this.start();
    }
    public void run(){
        for(Schedule schedule : bus.schedules) {
            if(schedule.departureSchedule.equals(timestamp)) {
                System.out.println(this.getName() + " ID : " + this.getId());
                String seat = Payment.makeBooking(timestamp, "RS01", bus) ? "Success" : "Failed";
                System.out.println(this.getName() + " ID : " + this.getId() + " " + seat);
            }
        }
    }

}
