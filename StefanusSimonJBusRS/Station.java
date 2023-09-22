package StefanusSimonJBusRS;


/**
 * Modul 3
 * Stefanus Simon Rilando
 */
public class Station extends Serializable
{
    public City city;
    public String stationName;
    
    public Station(int id, String stationName, City city){
       super(id);
       this.stationName = stationName;
       this.city = city;
    }
    
    public String print(){
        String println = "Station info: " + " | Station name: " + stationName + " | City: " + city.toString();
        return println;
    }
    
}
