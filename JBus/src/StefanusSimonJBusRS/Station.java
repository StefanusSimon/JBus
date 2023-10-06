package StefanusSimonJBusRS;


/**
 * Modul 3
 * Stefanus Simon Rilando
 */
public class Station extends Serializable
{
    public City city;
    public String stationName;
    public String address;
    
    public Station(String stationName, City city, String address){
       super();
       this.stationName = stationName;
       this.city = city;
       this.address = address;
    }
    
    public String toString(){
        String println = "Station info: " + " Id: " + id + " | Station name: " + stationName + " | City: " + city.toString();
        return println;
    }
    
}
