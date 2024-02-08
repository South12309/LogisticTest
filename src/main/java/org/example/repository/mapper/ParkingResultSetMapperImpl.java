package org.example.repository.mapper;

import org.example.model.DriverEntity;
import org.example.model.ParkingEntity;

import java.sql.ResultSet;
import java.util.List;

public class ParkingResultSetMapperImpl implements ParkingResultSetMapper {

    @Override
    public ParkingEntity mapOneResult(ResultSet resultSet) {
        return null;
    }

    @Override
    public List<ParkingEntity> mapListResult(ResultSet resultSet) {
        return null;
    }
}
