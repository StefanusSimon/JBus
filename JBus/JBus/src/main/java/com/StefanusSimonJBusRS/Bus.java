package com.StefanusSimonJBusRS;
import com.StefanusSimonJBusRS.dbjson.Serializable;

import java.util.List;
import java.util.ArrayList;
import java.sql.Timestamp;


/**
* Modul 4
* Stefanus Simon Rilando - 2206830422
*/
public class Bus extends Serializable
{
   public int capacity;
   public Facility facility;
   public String name;
   public Price price;
   public BusType busType;
   public City city;
   public Station departure;
   public Station arrival;
   public List<Schedule> schedules;
   
   public Bus (String name, Facility facility, Price price, int capacity, BusType busType, City city, Station departure, Station arrival){
       super();
       this.name = name;
       this.facility = facility;
       this.price = price;
       this.capacity = capacity;
       this.busType = busType;
       this.city = city;
       this.departure = departure;
       this.arrival = arrival;
       this.schedules = new ArrayList<>();
   }
   
   public String toString(){
       String println = "Bus Info: " + " | Id: " + id + " | Name: " + name + " | Facility: " + facility + " | Price: " + price + " | Capacity: " + capacity + " | Bus Type: " + busType + " | City: " + city + " | Departure: " + departure + " | Arrival: " + arrival;
       return println;
   }
   
   public Object write(){
       return null;
   }
   
    public Boolean read(String obj){
        return false;
    }

    public void addSchedule(Timestamp timestamp) {
        boolean isExisting = false;
        Schedule schedule = new Schedule(timestamp, capacity);
        for (Schedule sched : schedules) {
            if (sched.departureSchedule.equals(schedule.departureSchedule)) {
                isExisting = true;
            }
        }

        if (!isExisting) {
            schedules.add(schedule);
        }
    }
}
