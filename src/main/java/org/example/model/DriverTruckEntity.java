package org.example.model;

public class DriverTruckEntity {
    private int id;
    private int driverId;
    private int truckId;

    public DriverTruckEntity(int id, int driverId, int truckId) {
        this.id = id;
        this.driverId = driverId;
        this.truckId = truckId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public int getTruckId() {
        return truckId;
    }

    public void setTruckId(int truckId) {
        this.truckId = truckId;
    }
}
