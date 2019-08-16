package com.example.myapp.finalproject.model;

/**
 * Created by Raha on 12/16/2017.
 */

public class OrderDetails {

    private int OrderDetails_OrdeID ;
    private int OrderDetails_PersonID;
    private int OrderDetails_UnitPrice;
    private int OrderDetails_Quantity;

    public int getOrderDetails_OrdeID() {
        return OrderDetails_OrdeID;
    }

    public void setOrderDetails_OrdeID(int orderDetails_OrdeID) {
        OrderDetails_OrdeID = orderDetails_OrdeID;
    }

    public int getOrderDetails_PersonID() {
        return OrderDetails_PersonID;
    }

    public void setOrderDetails_PersonID(int orderDetails_PersonID) {
        OrderDetails_PersonID = orderDetails_PersonID;
    }

    public int getOrderDetails_UnitPrice() {
        return OrderDetails_UnitPrice;
    }

    public void setOrderDetails_UnitPrice(int orderDetails_UnitPrice) {
        OrderDetails_UnitPrice = orderDetails_UnitPrice;
    }

    public int getOrderDetails_Quantity() {
        return OrderDetails_Quantity;
    }

    public void setOrderDetails_Quantity(int orderDetails_Quantity) {
        OrderDetails_Quantity = orderDetails_Quantity;
    }
}
