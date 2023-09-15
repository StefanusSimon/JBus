package StefanusSimonJBusRS;


/**
* Modul 2
* Stefanus Simon Rilando - 2206830422
*/
public class Bus
{
   public int capacity;
   public Facility facility;
   public String name;
   public Price price;
   
   public Bus (String name, Facility facility, Price price, int capacity){
       this.name = name;
       this.facility = facility;
       this.price = price;
       this.capacity = capacity;
   }
}
