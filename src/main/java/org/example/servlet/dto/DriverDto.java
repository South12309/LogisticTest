package org.example.servlet.dto;

import org.example.model.TruckEntity;

import java.util.List;

public class DriverDto {
    private int id;
    private String fio;
    private List<TruckDto> trucks;

    public DriverDto() {
    }

    public DriverDto(int id, String fio, List<TruckDto> trucks) {
        this.id = id;
        this.fio = fio;
        this.trucks = trucks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public List<TruckDto> getTrucks() {
        return trucks;
    }

    public void setTrucks(List<TruckDto> trucks) {
        this.trucks = trucks;
    }
}
