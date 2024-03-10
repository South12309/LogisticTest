package org.example.servlet.dto;

import java.util.List;

public class DriverDto {
    private int id;
    private String surname;
    private String name;
    private String patronymic;
    private List<TruckDto> trucks;

    public DriverDto() {
    }

    public DriverDto(int id, String surname, String name, String patronymic, List<TruckDto> trucks) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.trucks = trucks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public List<TruckDto> getTrucks() {
        return trucks;
    }

    public void setTrucks(List<TruckDto> trucks) {
        this.trucks = trucks;
    }
}
