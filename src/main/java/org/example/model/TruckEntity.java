package org.example.model;

import java.util.List;

public class TruckEntity {
    private int id;
    private String model;
    private String number;
    private List<DriverEntity> drivers;
    private int parkingId;

    public TruckEntity(int id, String model, String number, List<DriverEntity> drivers, int parkingId) {
        this.id = id;
        this.model = model;
        this.number = number;
        this.drivers = drivers;
        this.parkingId = parkingId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<DriverEntity> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<DriverEntity> drivers) {
        this.drivers = drivers;
    }

    public int getParkingId() {
        return parkingId;
    }

    public void setParkingId(int parkingId) {
        this.parkingId = parkingId;
    }
}
