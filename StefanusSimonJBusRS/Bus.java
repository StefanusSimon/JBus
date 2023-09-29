package StefanusSimonJBusRS;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.text.SimpleDateFormat;


/**
* Modul 4
* Stefanus Simon Rilando - 2206830422
*/
public class Bus extends Serializable implements FileParser
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
   
   public Bus (int id, String name, Facility facility, Price price, int capacity, BusType busType, City city, Station departure, Station arrival){
       super(id);
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
   
   public void addSchedule(Calendar calendar){
       Schedule schedule = new Schedule (calendar, capacity);
       schedules.add(schedule);
   }
   
   public void printSchedule(Schedule schedule){
       SimpleDateFormat format = new SimpleDateFormat(" 'Date' : MMMM, dd, yyyy HH:mm:ss");
       System.out.println(format.format(schedule.depatureSchedule.getTime()));
       
       for(String string : schedule.seatAvailability.keySet()){
           String formatForm = string + " : " + String.valueOf(schedule.seatAvailability.get(string));
           System.out.println(formatForm);
       }
   }
   
   public Object write(){
       return null;
   }
   
    public Boolean read(String obj){
        return false;
    }
}
