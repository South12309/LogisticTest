package org.example.servlet.dto;

import org.example.model.TruckEntity;

import java.util.List;

public class ParkingDto {
    private int id;
    private String address;
    private int square;
    private List<TruckDto> trucks;

    public ParkingDto() {
    }

    public ParkingDto(int id, String address, int square, List<TruckDto> trucks) {
        this.id = id;
        this.address = address;
        this.square = square;
        this.trucks = trucks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSquare() {
        return square;
    }

    public void setSquare(int square) {
        this.square = square;
    }

    public List<TruckDto> getTrucks() {
        return trucks;
    }

    public void setTrucks(List<TruckDto> trucks) {
        this.trucks = trucks;
    }
}
