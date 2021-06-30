package com.repaircenter.model;

public class Client {
    String barcode;
    String name;
    String phone_number;

    public Client(String barcode,String name, String phone_number){
        this.barcode = barcode;
        this.name = name;
        this.phone_number = phone_number;
    }
    public Client(){}

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getName() {
        return name;
    }

    public String getPhone_number() {
        return phone_number;
    }
}
