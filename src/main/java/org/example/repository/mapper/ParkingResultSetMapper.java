package org.example.repository.mapper;

import org.example.model.DriverEntity;
import org.example.model.ParkingEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ParkingResultSetMapper {
    ParkingEntity mapOneResult(ResultSet resultSet) throws SQLException;
    List<ParkingEntity> mapListResult(ResultSet resultSet) throws SQLException;
}
