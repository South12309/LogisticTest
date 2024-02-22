package org.example.repository.mapper;

import org.example.model.DriverEntity;
import org.example.model.ParkingEntity;

import java.sql.ResultSet;
import java.util.List;

public class ParkingResultSetMapperImpl implements ParkingResultSetMapper {
    private static ParkingResultSetMapper INSTANCE;

    public static ParkingResultSetMapper getINSTANCE() {
        if (INSTANCE==null) {
            INSTANCE = new ParkingResultSetMapperImpl();
        }
        return INSTANCE;
    }
    private ParkingResultSetMapperImpl() {

    }

    @Override
    public ParkingEntity mapOneResult(ResultSet resultSet) {
        return null;
    }

    @Override
    public List<ParkingEntity> mapListResult(ResultSet resultSet) {
        return null;
    }
}
