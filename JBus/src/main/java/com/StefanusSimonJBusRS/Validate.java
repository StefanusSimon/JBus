package com.StefanusSimonJBusRS;
import java.util.ArrayList;


/**
* Modul 4
* Stefanus Simon Rilando - 2206830422
*/
public class Validate
{
  public static ArrayList filter(Price[]list, int value, boolean less) {
    ArrayList <Double> validateList = new ArrayList<>();

    if (less == true) {
        for (int i = 0; i < list.length; i++) {
            if (list[i].price < value) {
                validateList.add (list[i].price);
                }
            }
        } else if (less == false) {
            for (int i = 0; i < list.length; i++) {
                if (list[i].price > value) {
                    validateList.add (list[i].price);
                }
            }
        }
        return validateList;
    }
  
}
