package org.example.servlet.dto;

import org.example.model.TruckEntity;

import java.util.List;

public class ParkingDto {
    private int id;
    private String address;
    private int square;
    private List<TruckEntity> trucks;
}
