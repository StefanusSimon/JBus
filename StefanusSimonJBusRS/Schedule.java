package StefanusSimonJBusRS;
import java.util.Calendar;
import java.util.Map;
import java.util.LinkedHashMap;


/**
* Modul 4
* Stefanus Simon Rilando - 2206830422
*/
public class Schedule
{
    public Calendar departureSchedule;
    public Map< String, Boolean > seatAvailability;
    
    public Schedule(Calendar departureSchedule, int numberOfSeats){
        this.departureSchedule = departureSchedule;
        initializeSeatAvailability(numberOfSeats);
    }
    
    private void initializeSeatAvailability (int numberOfSeats){
        seatAvailability = new LinkedHashMap<>();
        for(int seatCount = 1; seatCount <= numberOfSeats; seatCount ++){
            seatAvailability.put("AF" + seatCount, true );
        }
    }
}
