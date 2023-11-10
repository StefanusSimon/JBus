package com.StefanusSimonJBusRS.controller;

import com.StefanusSimonJBusRS.Account;
import com.StefanusSimonJBusRS.Renter;
import org.springframework.web.bind.annotation.*;





@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account> {
    @GetMapping
    String index() { return "account page"; }

    @PostMapping("/register")
    Account register
            (
                    @RequestParam String name,
                    @RequestParam String email,
                    @RequestParam String password
            )
    {
        return new Account(name, email, password);
    }

    @GetMapping("/{id}")
    String getById (@PathVariable int id) { return "account id " + id + " not found!"; }

    public BaseResponse<Account> register (String, String,String){

    }

    public BaseResponse<Double> topUp (int, Double){

    }

    public BaseResponse<Account> login (String, String){

    }

    public BaseResponse<Renter> registerRenter (int, String, String, String){

    }

    public BaseResponse<Account> getJsonTable (){

    }
}
