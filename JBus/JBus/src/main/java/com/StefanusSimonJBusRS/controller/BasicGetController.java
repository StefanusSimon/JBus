package com.StefanusSimonJBusRS.controller;

import com.StefanusSimonJBusRS.Algorithm;
import com.StefanusSimonJBusRS.dbjson.JsonTable;
import com.StefanusSimonJBusRS.dbjson.Serializable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface BasicGetController <T extends Serializable> {
    public abstract JsonTable<T> getJsonTable();

    @GetMapping("/{id}")
    default String getById(@PathVariable int id){
        return Algorithm.<T>find(getJsonTable(), (e) -> {
            return e.id == id;
        }).toString();
    }
    @GetMapping("/page")
    public default List<T> getPage(int page,int pageSize){
        return Algorithm.<T>paginate(getJsonTable(), page, pageSize, (e) ->{;
            return true;
        });
    }
}
