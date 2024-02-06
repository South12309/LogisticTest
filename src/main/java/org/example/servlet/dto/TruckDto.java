package org.example.servlet.dto;

import org.example.model.DriverEntity;

import java.util.List;

public class TruckDto {
    private int id;
    private String model;
    private String number;
    private List<DriverEntity> drivers;
    private int parkingId;
}
