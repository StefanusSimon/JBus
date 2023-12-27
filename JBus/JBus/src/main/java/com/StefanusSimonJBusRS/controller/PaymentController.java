package com.StefanusSimonJBusRS.controller;

import com.StefanusSimonJBusRS.*;
import com.StefanusSimonJBusRS.dbjson.JsonAutowired;
import com.StefanusSimonJBusRS.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;

import static com.StefanusSimonJBusRS.Algorithm.find;

@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController <Payment>{

    @JsonAutowired(value = Payment.class, filepath = "D:\\JBusAndroid Anus\\src\\main\\java\\com\\StefanusSimonJBusRS\\json\\payment.json")
    public static JsonTable<Payment> paymentTable;

    static {
        try {
            paymentTable = new JsonTable<>(Payment.class, "D:\\JBusAndroid Anus\\src\\main\\java\\com\\StefanusSimonJBusRS\\json\\payment.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value="/makeBooking", method= RequestMethod.POST)
    public BaseResponse<Payment> makeBooking(
            @RequestParam int buyerId,
            @RequestParam int busId,
            @RequestParam List<String> busSeats,
            @RequestParam String departureDate){
        Predicate<Account> p1 = a -> a.id == buyerId;
        Predicate<Bus> p2 = b -> b.id == busId;
        Account buyer = Algorithm.find(AccountController.accountTable, p1);
        Bus bus = Algorithm.find(BusController.busTable, p2);
        if(buyer == null){
            return new BaseResponse<>(false, "Account not found", null);
        }
        if(bus == null){
            return new BaseResponse<>(false, "Bus not found", null);
        }
        if(buyer.balance < bus.price.price * busSeats.size()){
            return new BaseResponse<>(false, "Insufficient balance", null);
        }
        Timestamp scheduleTime;
        try {
            DateTimeFormatter formatterChecker = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            scheduleTime = Timestamp.valueOf(LocalDateTime.parse(departureDate, formatterChecker));
        } catch (DateTimeParseException e) {
            return new BaseResponse<>(false, "Invalid time format", null);
        }

        Predicate<Schedule> p3 = s -> s.departureSchedule.equals(scheduleTime);
        Schedule schedule = Algorithm.find(bus.schedules.iterator(), p3);
        if(schedule == null){
            return new BaseResponse<>(false, "Schedule not found", null);
        }

        if(Payment.makeBooking(scheduleTime, busSeats, bus)){
            Payment payment = new Payment(buyerId, bus.id, busId, busSeats, scheduleTime);
            payment.status = Invoice.PaymentStatus.WAITING;
            paymentTable.add(payment);
            return new BaseResponse<>(true, "Booking made", payment);
        }

        return new BaseResponse<>(false, "Cant make booking", null);
    }

    @RequestMapping(value="/{id}/accept", method= RequestMethod.POST)
    public BaseResponse<Payment> accept(@PathVariable int id){
        Predicate<Payment> p = p1 -> p1.id == id;
        Payment payment = Algorithm.find(paymentTable, p);
        if(payment == null){
            return new BaseResponse<>(false, "Payment not found", null);
        }
        if(payment.status != Invoice.PaymentStatus.WAITING){
            return new BaseResponse<>(false, "Payment not waiting", null);
        }
        payment.status = Invoice.PaymentStatus.SUCCESS;
        Predicate<Account> p2 = a -> a.id == payment.buyerId;
        Account buyer = Algorithm.find(AccountController.accountTable, p2);
        double totalAmount = payment.busSeats.size() * payment.getBus().price.price;
        buyer.balance -= totalAmount;
        return new BaseResponse<>(true, "Payment accepted", payment);
    }
    @RequestMapping(value="/{id}/cancel", method= RequestMethod.POST)
    public BaseResponse<Payment> cancel(@PathVariable int id){
        Predicate<Payment> p = p1 -> p1.id == id;
        Payment payment = Algorithm.find(paymentTable, p);
        Predicate<Account> buyerPredicate = a -> a.id == payment.buyerId;
        Account buyer = Algorithm.find(AccountController.accountTable, buyerPredicate);
        double totalAmount = payment.busSeats.size() * payment.getBus().price.price;
        buyer.balance += totalAmount;
        payment.status = Invoice.PaymentStatus.FAILED;
        Predicate<Bus> busPredicate = b -> b.id == payment.getBus().id;
        Bus bus = Algorithm.find(BusController.busTable, busPredicate);
        Predicate<Schedule> schedulePredicate = s -> s.departureSchedule.equals(payment.departureDate);
        Schedule schedule = Algorithm.find(bus.schedules, schedulePredicate);
        for(String seat : payment.busSeats){
            schedule.seatAvailability.put(seat, true);
        }
        return new BaseResponse<>(true, "Payment cancelled", payment);
    }

    @Override
    public JsonTable<Payment> getJsonTable() {
        return paymentTable;
    }

    @GetMapping("/getMyPayment")
    public BaseResponse<List<Payment>> getMyPayment(@RequestParam int accountId){
        Predicate<Payment> p = p1 -> p1.buyerId == accountId || p1.renterId == accountId;
        List<Payment> payments = Algorithm.collect(paymentTable, p);
        if(payments == null){
            return new BaseResponse<>(false, "Payment not found", null);
        }
        return new BaseResponse<>(true, "Payment found", payments);
    }

}