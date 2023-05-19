package com.blogspot.dastinsandura.server;

public class ResponseData {

    private int orderCost;

    public int getOrderCost() {
        return orderCost;
    }

    public void setOrderCost(int orderCost) {
        this.orderCost = orderCost;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "orderCost=" + orderCost +
                '}';
    }
}
