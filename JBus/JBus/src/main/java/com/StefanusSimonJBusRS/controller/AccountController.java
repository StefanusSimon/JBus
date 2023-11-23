package com.StefanusSimonJBusRS.controller;

import com.StefanusSimonJBusRS.Account;
import com.StefanusSimonJBusRS.Algorithm;
import com.StefanusSimonJBusRS.Renter;
import com.StefanusSimonJBusRS.dbjson.JsonAutowired;
import com.StefanusSimonJBusRS.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account> {

    @JsonAutowired(value = Account.class, filepath = "src\\main\\java\\com\\StefanusSimonJBusRS\\json\\accounts_db.json")
    public static JsonTable<Account> accountTable;

    static {
        try {
            accountTable = new JsonTable<>(Account.class, "src\\main\\java\\com\\StefanusSimonJBusRS\\json\\accounts_db.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping
    String index() { return "account page"; }

    public JsonTable<Account> getJsonTable() {
        return accountTable;
    }

    /*@GetMapping("/{id}")
    String getById (@PathVariable int id) { return "account id " + id + " not found!"; }*/

    @PostMapping("/register")
     BaseResponse<Account> register (
            @RequestParam String name,
            @RequestParam String password,
            @RequestParam String email)
    {Account user = new Account(name, email, password);
        if (user.validate() && (!name.isBlank()) && !Algorithm.<Account>exists(getJsonTable(), e -> e.email.equals(email))) {
            String passwordToHash = password;
            String generatedPassword = null;
            try{
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(passwordToHash.getBytes());
                byte[] bytes = md.digest();
                StringBuilder sb = new StringBuilder();
                for(int i = 0; i < bytes.length; i++){
                    sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                }
                generatedPassword = sb.toString();
            }catch (NoSuchAlgorithmException e){
                e.printStackTrace();
            }
            user.password = generatedPassword;
            accountTable.add(user);
            return new BaseResponse<>(true, "Register Berhasil", user);
        }
        return new BaseResponse<>(false, "Register Gagal", null);
    }

    @PostMapping("/{id}/topUp")
    BaseResponse<Double> topUp (
            @PathVariable int id,
            @RequestParam double amount)
    {for(Account x : accountTable) {
        if(x.id == id && x.topUp(amount)) {
            return new BaseResponse<>(true, "Success", amount);
        }
    }

        return new BaseResponse<>(false, "Unsuccess", 0.0D);
    }

    @PostMapping("/login")
    BaseResponse<Account> login (
            @RequestParam String email,
            @RequestParam String password)

    {String passwordToHash = password;
        String generatedPassword = null;
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(passwordToHash.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < bytes.length; i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        for (Account y : accountTable) {
        if (y.email.equals(email) && y.password.equals(generatedPassword)) {
            return new BaseResponse<>(true, "Login Success", y);
        }
    }
        return new BaseResponse<>(false, "Login Unsuccessful", null);
    }

    @PostMapping("/{id}/registerRenter")
    BaseResponse<Renter> registerRenter (
            @PathVariable int id,
            @RequestParam String companyName,
            @RequestParam String phoneNumber,
            @RequestParam String address)
    {for (Account z : accountTable) {
        if (z.id == id && z.company == null) {
            Renter renter = new Renter(companyName, phoneNumber, address);
            z.company = renter;
            return new BaseResponse<>(true, "Register Berhasil", renter);
        }
    }
        return new BaseResponse<>(false, "Register Gagal", null);
    }
}

