package org.example.repository.mapper;

import org.example.model.DriverEntity;
import org.example.model.ParkingEntity;

import java.sql.ResultSet;

public interface ParkingResultSetMapper {
    ParkingEntity map(ResultSet resultSet);
}
