package com.StefanusSimonJBusRS;


import com.StefanusSimonJBusRS.dbjson.Serializable;

import java.util.regex.Pattern;

/**
 * Modul 3
 * Stefanus Simon Rilando - 2206830422
 */
public class Renter extends Serializable
{
    public String address;
    public String companyName;
    public String phoneNumber;

    private final String REGEX_NAME = "^[A-Z][a-zA-Z0-9_]{3,19}$";

    private  final String REGEX_PHONE = "^[0-9]{9,12}$";
    
    public Renter(String companyName){
       super();
       this.companyName = companyName;
        this.phoneNumber = "";
        this.address = "";
    }
    
    public Renter(String companyName, String phoneNumber){
       super();
       this.companyName = companyName;
       this.phoneNumber = phoneNumber;
        this.address = "";
    }
    
    public Renter(String companyName, String phoneNumber, String address){
       super();
       this.companyName = companyName;
       this.phoneNumber = phoneNumber;
       this.address = address;
    }

    public boolean validate(){
        boolean phone = Pattern.matches(REGEX_PHONE, String.valueOf(phoneNumber));
        boolean name = Pattern.matches(REGEX_NAME, String.valueOf(companyName));

        if(!phone) {
            System.out.println("Phone Number Not Valid");
        }

        if(!name){
            System.out.println("Company Name Not Valid");
        }

        return name && phone;
    }
}
