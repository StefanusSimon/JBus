package com.StefanusSimonJBusRS;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.text.SimpleDateFormat;


/**
* Modul 4
* Stefanus Simon Rilando - 2206830422
*/
public class Schedule
{
    public Timestamp departureSchedule;
    public Map< String, Boolean > seatAvailability;
    
    public Schedule(Timestamp departureSchedule, int numberOfSeats){
        this.departureSchedule = departureSchedule;
        initializeSeatAvailability(numberOfSeats);
    }
    
    private void initializeSeatAvailability (int numberOfSeats){
        this.seatAvailability = new LinkedHashMap<>();
        
        for (int seatNumber = 1; seatNumber <= numberOfSeats; seatNumber ++){
            String sn = seatNumber < 10 ? "0"+seatNumber : ""+seatNumber;
            seatAvailability.put("RS" + sn, true);
        }
    }
    
    public void printSchedule() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy HH:mm:ss");
        String formattedDepartureSchedule = dateFormat.format(this.departureSchedule.getTime());
        System.out.println("Tanggal keberangkatan: " + formattedDepartureSchedule);
        System.out.println("Daftar kursi dan ketersediaan kursinya: ");

        int maxSeatsPerRow = 4;
        int currentSeat = 1;

        for (String seat : this.seatAvailability.keySet()) {
            String symbol = this.seatAvailability.get(seat)? "O" : "X";
            System.out.print(seat + " : " + symbol + "\t");
            if (currentSeat % maxSeatsPerRow == 0) {
                System.out.println();
            }
            currentSeat ++;
            }
        System.out.println("\n");
    }

    public boolean isSeatAvailable (String seat){
        System.out.println(seat + " " + (seatAvailability.containsKey(seat) && seatAvailability.get(seat)));
        return seatAvailability.containsKey(seat) && seatAvailability.get(seat);
    }
   
   public void bookSeat(String seat){
       seatAvailability.put(seat, false);
   }

    public boolean isSeatAvailable(List<String> seats){
        for(String seat : seats){
            if(!isSeatAvailable(seat)){
                return false;
            }
        }
        return true;
    }

   public void bookSeat(List<String>seat){
       for(String i : seat) {
           seatAvailability.put(i, false);
       }
   }

   public String toString(){
       int println = Algorithm.count(seatAvailability.values().iterator(), false);
       return "Schedule\t: " + departureSchedule + "\nOccupied\t: " + println + "/" + seatAvailability.size();
   }
}
