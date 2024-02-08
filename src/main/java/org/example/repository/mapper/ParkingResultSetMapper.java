package org.example.repository.mapper;

import org.example.model.DriverEntity;
import org.example.model.ParkingEntity;

import java.sql.ResultSet;
import java.util.List;

public interface ParkingResultSetMapper {
    ParkingEntity mapOneResult(ResultSet resultSet);
    List<ParkingEntity> mapListResult(ResultSet resultSet);
}
