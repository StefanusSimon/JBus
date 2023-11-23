package com.StefanusSimonJBusRS.controller;

import com.StefanusSimonJBusRS.*;
import com.StefanusSimonJBusRS.dbjson.JsonAutowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.StefanusSimonJBusRS.dbjson.JsonTable;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;


@RestController
@RequestMapping("/bus")
public class BusController implements BasicGetController <Bus> {

    @JsonAutowired(value = Bus.class, filepath = "src\\main\\java\\com\\StefanusSimonJBusRS\\json\\account.json")
    public static JsonTable<Bus> busTable;

    static {
        try {
            busTable = new JsonTable<>(Bus.class, "src\\main\\java\\com\\StefanusSimonJBusRS\\json\\account.json");
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
            @RequestParam String time
    ){for(Bus x : getJsonTable()) {
        if(x.id == busId) {
            try {
                x.addSchedule(Timestamp.valueOf(time));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            return new BaseResponse<>(true, "Berhasil", x);
        }
    }
        return new BaseResponse<>(false, "Tidak Berhasil", null);
    }


}
