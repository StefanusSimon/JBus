package com.StefanusSimonJBusRS;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.StefanusSimonJBusRS.dbjson.JsonDBEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*import com.google.gson.*;
import com.google.gson.reflect.TypeToken;*/


/**
 * Modul 4
 * Stefanus Simon Rilando - 2206830422
 */

@SpringBootApplication
public class JBus
{
    public static void main(String[] args) throws InterruptedException{
        JsonDBEngine.Run(JBus.class);
        SpringApplication.run(JBus.class, args);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> JsonDBEngine.join()));
        /*try {
            Bus bus = createBus();
            bus.schedules.forEach(Schedule::printSchedule);
            for (int x = 0; x < 10; x++) {
                BookingThread thread = new BookingThread("Thread" + x, bus, Timestamp.valueOf("2023-07-27 19:00:00"));
                Thread.sleep(1000);
            }
            bus.schedules.forEach(Schedule::printSchedule);
        }catch (InterruptedException e){
            e.printStackTrace();
        }*/
       /*try{
            String filewritepath = "C:\\Users\\user\\Downloads\\bluej\\JBus\\JBus\\data\\AccountDatabase.json";
            JsonTable <Account> tableAccount = new JsonTable<>(Account.class, filewritepath);
            Account account = new Account("Stefanus Simon Rilando", "stefanus@gmail.com", "sT164Pen");
            tableAccount.add(account);
            tableAccount.forEach(e -> System.out.println(e.toString()));
            tableAccount.writeJson();
       }catch (Exception e){
           e.printStackTrace();
        }*/
    }

    public static Bus createBus() {
        Price price = new Price(750000, 5);
        Bus bus = new Bus("Netlab Bus", Facility.LUNCH, price, 25, BusType.REGULER, City.BANDUNG,
                new Station("Depok Terminal", City.DEPOK, "Jl. Margonda Raya"),
                new Station("Halte UI", City.JAKARTA, "Universitas Indonesia"));
        Timestamp timestamp = Timestamp.valueOf("2023-07-27 19:00:00");
        try{
            bus.addSchedule(timestamp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bus;
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

    public static int getBusId(){
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
    }

    /*public static Bus createBus() {
        Price price = new Price(750000, 5);
        Bus bus = new Bus("Netlab Bus", Facility.LUNCH, price, 25, BusType.REGULER, City.BANDUNG, new Station("Depok Terminal", City.DEPOK, "Jl. Margonda Raya"), new Station("Halte UI", City.JAKARTA, "Universitas Indonesia"));
        return bus;
    }*/

}


