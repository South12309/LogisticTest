package org.example.repository.mapper;

import org.example.model.DriverEntity;

import java.sql.ResultSet;
import java.util.List;

public interface DriverTruckResultSetMapper {
    List<DriverEntity> mapListResult(ResultSet resultSet);

    DriverEntity mapOneResult(ResultSet resultSet);
}
