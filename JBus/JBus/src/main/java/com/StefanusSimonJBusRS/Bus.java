package com.StefanusSimonJBusRS;

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
   public List<Facility>facilities;
   public String name;
   public Price price;
   public BusType busType;
   public Station departure;
   public Station arrival;
   public List<Schedule> schedules;
   public int accountId;

    public Bus(int accountId,String name, List<Facility> facilities, Price price, int capacity, BusType busType, Station departure, Station arrival)
    {
        super();
        this.accountId = accountId;
        this.capacity = capacity;
        this.facilities = facilities;
        this.price = price;
        this.name = name;
        this.busType = busType;
        this.departure = departure;
        this.arrival = arrival;
        this.schedules = new ArrayList<Schedule>();
    }
   public String toString(){
       String println = "Bus Info: " + " | Id: " + id + " | Name: " + name + " | Facility: " + facilities + " | Price: " + price + " | Capacity: " + capacity + " | Bus Type: " + busType + " | Departure: " + departure + " | Arrival: " + arrival;
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
