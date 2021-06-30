package com.repaircenter.model;

public class Phone {
    String barcode;
    String phone_model;
    String imei;
    String description;
    String status;

    public Phone(String barcode, String phone_model, String imei, String description, String status) {
        this.barcode = barcode;
        this.phone_model = phone_model;
        this.imei = imei;
        this.description = description;
        this.status = status;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setPhone_model(String phone_model) {
        this.phone_model = phone_model;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getPhone_model() {
        return phone_model;
    }

    public String getImei() {
        return imei;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }
}
