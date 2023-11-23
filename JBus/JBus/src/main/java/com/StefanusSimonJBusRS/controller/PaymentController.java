package com.StefanusSimonJBusRS.controller;

import com.StefanusSimonJBusRS.*;
import com.StefanusSimonJBusRS.dbjson.JsonAutowired;
import com.StefanusSimonJBusRS.dbjson.JsonTable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import static com.StefanusSimonJBusRS.Algorithm.find;

public class PaymentController implements BasicGetController <Payment>{

    @JsonAutowired(value = Payment.class, filepath = "src\\main\\java\\com\\StefanusSimonJBusRS\\json\\account.json")
    public static JsonTable<Payment> paymentTable;

    static {
        try {
            paymentTable = new JsonTable<>(Payment.class, "src\\main\\java\\com\\StefanusSimonJBusRS\\json\\account.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/makeBooking")
    public BaseResponse<Payment> makeBooking(
            @RequestParam int buyerId,
            @RequestParam int renterId,
            @RequestParam int busId,
            @RequestParam List<String> busSeats,
            @RequestParam String departureDate
    ){
        Account buyers = Algorithm.<Account>find(AccountController.accountTable, e -> e.id==buyerId);
        Bus bus = Algorithm.<Bus>find(BusController.busTable, e -> e.id==busId);

        if (buyers != null && bus != null) {
            Payment.makeBooking(Timestamp.valueOf(departureDate), busSeats, bus);
            Payment payment = new Payment(buyerId, renterId, busId, busSeats, Timestamp.valueOf(departureDate));
            paymentTable.add(payment);
            return new BaseResponse<>(true, "Success Make a book!", payment);
        }
        return new BaseResponse<>(false, "Failed To book!", null);


    }

    @PostMapping("/{id}/accept")
    BaseResponse<Payment> accept(
            @PathVariable int id
    ) {
        Payment payment =Algorithm.<Payment>find(paymentTable, e -> e.id==id);
        if (payment != null){
            payment.status = Invoice.PaymentStatus.SUCCESS;
            return new BaseResponse<>(true, "Payment Accepted", payment);
        }
        return new BaseResponse<>(false, "Cannot Accept Payment", null);
    }
    @PostMapping("/{id}/cancel")
    BaseResponse<Payment> cancel(
            @PathVariable int id
    ) {
        Payment payment =Algorithm.<Payment>find(paymentTable, e -> e.id==id);
        if (payment != null){
            payment.status = Invoice.PaymentStatus.FAILED;
            return new BaseResponse<Payment>(true, "Payment Cancelled", payment);
        }
        return new BaseResponse<Payment>(false, "Cannot cancel the payment", null);
    }

    @Override
    public JsonTable<Payment> getJsonTable() {
        return paymentTable;
    }
}
