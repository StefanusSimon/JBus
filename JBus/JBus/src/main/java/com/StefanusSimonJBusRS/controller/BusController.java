package com.StefanusSimonJBusRS.controller;

import com.StefanusSimonJBusRS.*;
import com.StefanusSimonJBusRS.dbjson.JsonAutowired;
import org.springframework.web.bind.annotation.*;
import com.StefanusSimonJBusRS.dbjson.JsonTable;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;


@RestController
@RequestMapping("/bus")
public class BusController implements BasicGetController <Bus> {

    @JsonAutowired(value = Bus.class, filepath = "D:\\JBusAndroid Anus\\src\\main\\java\\com\\StefanusSimonJBusRS\\json\\bus.json")
    public static JsonTable<Bus> busTable;

    static {
        try {
            busTable = new JsonTable<>(Bus.class, "D:\\JBusAndroid Anus\\src\\main\\java\\com\\StefanusSimonJBusRS\\json\\bus.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public JsonTable<Bus> getJsonTable() {
        return busTable;
    }
    @PostMapping("/create")
    public BaseResponse<Bus> create(
            @RequestParam int accountId,
            @RequestParam String name,
            @RequestParam int capacity,
            @RequestParam List<Facility> facilities,
            @RequestParam BusType busType,
            @RequestParam int price,
            @RequestParam int stationDepartureId,
            @RequestParam int stationArrivalId
    ){ Account account = Algorithm.<Account>find(AccountController.accountTable, acc->acc.id == accountId && acc.company != null);
        if (account == null){
            return new BaseResponse<>(false,"Account tidak berhasil ditemukan dan bukan renter",null);
        }
        Station departure = Algorithm.<Station>find(StationController.stationTable,station -> station.id == stationDepartureId);
        Station arrival = Algorithm.<Station>find(StationController.stationTable,station -> station.id == stationArrivalId);
        if (departure == null && arrival == null ){
            return new BaseResponse<>(false, "Stasiun departure dan arrival tidak berhasil ditambahkan",null);
        }
        Bus bus = new Bus(accountId, name, facilities, new Price(price), capacity, busType, departure, arrival);
        busTable.add(bus);
        return new BaseResponse<>(true,"Stasiun berhasil ditambah",bus);

    }

    @PostMapping("/addSchedule")
    public BaseResponse<Bus> addSchedule(
            @RequestParam int busId,
            @RequestParam String time){
        Predicate<Bus> p = b -> b.id == busId;
        if(!Algorithm.exists(busTable, p)){
            return new BaseResponse<>(false, "Bus not found", null);
        }
        Bus bus = Algorithm.find(busTable, p);
        System.out.println(time);
        Timestamp scheduleTime;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            scheduleTime = Timestamp.valueOf(LocalDateTime.parse(time, formatter));
        } catch (DateTimeParseException e) {
            return new BaseResponse<>(false, "Invalid time format", null);
        }
        bus.addSchedule(scheduleTime);
        return new BaseResponse<>(true, "Schedule added", bus);
    }

    @GetMapping("/getAllBus")
    public BaseResponse<List<Bus>> getAllBus(){
        return new BaseResponse<>(true, "Berhasil", getJsonTable());
    }

    @GetMapping("/getMyBus")
    public BaseResponse<List<Bus>> getMyBus(
            @RequestParam int accountId
    ){
        Account account = Algorithm.<Account>find(AccountController.accountTable, acc->acc.id == accountId && acc.company != null);
        if (account == null){
            return new BaseResponse<>(false,"Account tidak berhasil ditemukan dan bukan renter",null);
        }
        List<Bus> buses = Algorithm.<Bus>collect(getJsonTable(), bus -> bus.accountId == accountId);
        return new BaseResponse<>(true,"Berhasil",buses);
    }

}