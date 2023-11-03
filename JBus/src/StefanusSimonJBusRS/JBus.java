package StefanusSimonJBusRS;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;


/**
 * Modul 4
 * Stefanus Simon Rilando - 2206830422
 */
public class JBus
{
    public static void main(String[] args) {
        try {
            String filepath = "C:\\Users\\user\\Downloads\\bluej\\JBus\\JBus\\data\\buses_CS.json";
            JsonTable<Bus> busList = new JsonTable<>(Bus.class, filepath);
            List<Bus> filteredBus = filterByDeparture(busList, City.JAKARTA, 0, 3);
            filteredBus.forEach(bus -> System.out.println(bus.toString()));
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public static List<Bus> filterByDeparture(List<Bus> buses, City departure, int page, int pageSize) {
        List<Bus> list = new ArrayList<>();

        for(Bus bus : buses) {
            if(bus.departure.city == departure) {
                list.add(bus);
            }
        }
        List<Bus> pageList = Algorithm.paginate(list, page, pageSize, (e) -> {
            return true;
        });
        return pageList;
    }

    public static List<Bus> filterByPrice(List<Bus> buses, int min, int max){
        List<Bus> priceFilter = Algorithm.<Bus>collect(buses, (e) ->{
            return e.price.price >= min && e.price.price <= max;
        });
        return priceFilter;
    }

    public static Bus filterBusId(List<Bus> buses, int id){
        Bus busFilter = Algorithm.<Bus>find(buses, (e) ->{
            return e.id == id;
        });
        return busFilter;
    }

    public static Bus filterByDepartureAndArrival(List<Bus> buses, City departure, City arrival, int page, int pageSize){
        List<Bus> list = new ArrayList<>();
        for(Bus bus : buses){
            if(bus.arrival.city == arrival &&  bus.departure.city == departure){
                list.add(bus);
            }
        }
        List<Bus> pageList = Algorithm.<Bus>paginate(list, page, pageSize, (e) ->{
            return true;
        });
        return (Bus) pageList;
    }



    /*public static int getBusId(){
    return 0;
    }

    public static String getBusName(){
    return "Bus";
    }

    public static boolean isDiscount(){
    return true;
    }

    public static float getDiscountPercentage(int beforeDiscount, int afterDiscount){
        float discPercent;
        if (afterDiscount >= beforeDiscount){
         discPercent = 0.0f;
        } else {
            discPercent = (float)((beforeDiscount - afterDiscount) / (float)(beforeDiscount)) * 100;
        }
    return discPercent;
    }

    public static int getDiscountedPrice(int price, float discountPercentage){
        int discPrice = 0;
        if (discountPercentage > 100.0f){
            discountPercentage = 100.0f;
        } else {
            discPrice = (int)((100.0f - discountPercentage) * price) / 100;
        }
    return discPrice;
    }

    public static int getOriginalPrice(int discountedPrice, float discountPercentage){
        return  (int)((100 * discountedPrice) / (100.0f - discountPercentage));
    }

    public static float getAdminFeePercentage(){
    return 0.05f;
    }

    public static int getAdminFee(int price){
        float adminFeePercentage = getAdminFeePercentage();
        return (int)(price * adminFeePercentage);
    }

    public static int getTotalPrice(int price, int numberOfSeat){
    return (int) ((price * numberOfSeat) + getAdminFee(price * numberOfSeat));
    }*/

    public static Bus createBus() {
        Price price = new Price(750000, 5);
        Bus bus = new Bus("Netlab Bus", Facility.LUNCH, price, 25, BusType.REGULER, City.BANDUNG, new Station("Depok Terminal", City.DEPOK, "Jl. Margonda Raya"), new Station("Halte UI", City.JAKARTA, "Universitas Indonesia"));
        return bus;
    }

}


