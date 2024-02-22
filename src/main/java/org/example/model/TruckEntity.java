package org.example.model;

import java.util.List;

public class TruckEntity {
    private int id;
    private String model;
    private String number;
    private List<DriverEntity> drivers;
    private ParkingEntity parking;

    public TruckEntity() {
    }

    public TruckEntity(int id, String model, String number, List<DriverEntity> drivers, ParkingEntity parking) {
        this.id = id;
        this.model = model;
        this.number = number;
        this.drivers = drivers;
        this.parking = parking;
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

    public ParkingEntity getParking() {
        return parking;
    }

    public void setParking(ParkingEntity parking) {
        this.parking = parking;
    }
}
