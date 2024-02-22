package org.example.servlet.dto;

import org.example.model.DriverEntity;

import java.util.List;

public class TruckDto {
    private int id;
    private String model;
    private String number;
    private List<DriverDto> drivers;
    private ParkingDto parking;

    public TruckDto() {
    }

    public TruckDto(int id, String model, String number, List<DriverDto> drivers, ParkingDto parking) {
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

    public List<DriverDto> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<DriverDto> drivers) {
        this.drivers = drivers;
    }

    public ParkingDto getParking() {
        return parking;
    }

    public void setParkingId(ParkingDto parking) {
        this.parking = parking;
    }
}
