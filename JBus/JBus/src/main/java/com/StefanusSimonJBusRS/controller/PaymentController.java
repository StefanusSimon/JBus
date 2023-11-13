package com.StefanusSimonJBusRS.controller;

import com.StefanusSimonJBusRS.Account;
import com.StefanusSimonJBusRS.Algorithm;
import com.StefanusSimonJBusRS.Bus;
import com.StefanusSimonJBusRS.Payment;
import com.StefanusSimonJBusRS.dbjson.JsonAutowired;
import com.StefanusSimonJBusRS.dbjson.JsonTable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import static com.StefanusSimonJBusRS.Algorithm.find;

public class PaymentController implements BasicGetController <Payment>{

    @JsonAutowired(value = Payment.class, filepath = "C:\\Users\\USER\\Downloads\\OOP\\JBus\\JBus\\JBus\\src\\main\\java\\com\\StefanusSimonJBusRS\\json\\account.json")
    public static JsonTable<Payment> paymentTable;

    static {
        try {
            paymentTable = new JsonTable<>(Payment.class, "C:\\Users\\USER\\Downloads\\OOP\\JBus\\JBus\\JBus\\src\\main\\java\\com\\StefanusSimonJBusRS\\json\\account.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/makeBooking")
    /*public BaseResponse<Payment> makeBooking(
            @RequestParam int buyerId,
            @RequestParam int renterIs,
            @RequestParam int busId,
            @RequestParam List<String> busSeats,
            @RequestParam String depatureDate
    ){
       /* Account buyer = Algorithm.<~> find(AccountController.accountTable, x -> x.id == buyerId);
        Account renter = Algorithm.<> find(AccountController.accountTable, x -> x.company.id == renterId);
        Bus bus = Algorithm.<Bus>find(BusController.busTable, x -> (x.id == busId) && (x.accountId == renterId));
        if ((buyer != null) && (bus != null)){
            if(Payment.makeBooking(Timestamp.valueOf(depatureDate), busSeats, bus)){
                Payment payment = new Payment(buyer, renter.company, busId, busSeats,Timestamp.valueOf(depatureDate) )
            }
        }*/

    }*/

    @Override
    public JsonTable<Payment> getJsonTable() {
        return paymentTable;
    }
}
