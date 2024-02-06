package org.example.servlet.dto;

import org.example.model.TruckEntity;

import java.util.List;

public class DriverDto {
    private int id;
    private String fio;
    private List<TruckEntity> trucks;
}
