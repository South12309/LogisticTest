package org.example.model;

import java.util.List;

public class DriverEntity {
    private int id;
    private String fio;
    private List<TruckEntity> trucks;

    public DriverEntity() {
    }

    public DriverEntity(int id, String fio, List<TruckEntity> trucks) {
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

    public List<TruckEntity> getTrucks() {
        return trucks;
    }

    public void setTrucks(List<TruckEntity> trucks) {
        this.trucks = trucks;
    }
}
