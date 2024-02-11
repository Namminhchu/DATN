package com.example.ecommerce_mobile_app.model;

import androidx.databinding.BaseObservable;

import java.io.Serializable;
import java.time.Instant;

public class BookingService extends BaseObservable implements Serializable {

    private String id;
    private String clientId;
    private String serviceId;
    private String serviceCode;
    private String serviceName;
    private String fullname;
    private String phone;
    private String appointmentDate;

    private String petName;
    private String petType;
    private String serviceWorkerId;

    private String appointmentDateStr;

    private String workerName;

    private String statusDisplay;

    private float price;

    private String orderDateMobile;

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getAppointmentDateStr() {
        return appointmentDateStr;
    }

    public void setAppointmentDateStr(String appointmentDateStr) {
        this.appointmentDateStr = appointmentDateStr;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public String getServiceWorkerId() {
        return serviceWorkerId;
    }

    public void setServiceWorkerId(String serviceWorkerId) {
        this.serviceWorkerId = serviceWorkerId;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatusDisplay() {
        return statusDisplay;
    }

    public void setStatusDisplay(String statusDisplay) {
        this.statusDisplay = statusDisplay;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getOrderDateMobile() {
        return orderDateMobile;
    }

    public void setOrderDateMobile(String orderDateMobile) {
        this.orderDateMobile = orderDateMobile;
    }
}
