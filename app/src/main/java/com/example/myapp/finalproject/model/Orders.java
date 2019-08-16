package com.example.myapp.finalproject.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Raha on 12/16/2017.
 */

public class Orders {

    private int orderID;
    private String orderDate ;
    private int order_PersonID;

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrder_PersonID() {
        return order_PersonID;
    }
    public void setOrder_PersonID(int order_PersonID) {
        this.order_PersonID = order_PersonID;
    }


    public String calcDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        return dateFormat.format(date);
    }
}
