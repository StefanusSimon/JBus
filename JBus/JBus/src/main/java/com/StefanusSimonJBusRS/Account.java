package com.StefanusSimonJBusRS;


import com.StefanusSimonJBusRS.dbjson.Serializable;

import java.util.regex.Pattern;

/**
* Modul 4
 * Stefanus Simon Rilando - 2206830422
 */
public class Account extends Serializable
{
    public String email;
    public String name;
    public String password;

    public Renter company;
    public Double balance;

    private static final String REGEX_EMAIL = "^[a-zA-Z0-9]+@[a-zA-Z]+(\\.[a-zA-Z]+)+$";
    private static final String REGEX_PASSWORD = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
    
    public Account(String name, String email, String password){
        super();
        this.name = name;
        this.email = email;
        this.password = password;
        this.company = null;
        this.balance = 0.0D;
        validate();
    }
    
    public String toString(){
        String println = "Account info: " + " | Id: " + id + " | Email: " + email + " | Name: " + name + " | Password: " + password;
        return println;
    }

    public boolean validate(){
        boolean validateEmail = Pattern.matches(REGEX_EMAIL, String.valueOf(email));
        boolean validatePassword = Pattern.matches(REGEX_PASSWORD, String.valueOf(password));

        if(!validateEmail) {
            System.out.println("Email Username Number Not Valid");
        }

        if(!validatePassword){
            System.out.println("Password Not Valid");
        }

        return validateEmail && validatePassword;
    }

    public boolean topUp (double amount){
        if(amount > 0){
            this.balance += amount;
            return true;
        }
        return false;
    }
}
