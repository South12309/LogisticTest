package org.example.repository.mapper;

import org.example.model.DriverEntity;
import org.example.model.ParkingEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public ParkingEntity mapOneResult(ResultSet resultSet) throws SQLException {
        ParkingEntity parkingEntity = new ParkingEntity();
        parkingEntity.setId(resultSet.getInt("id"));
        parkingEntity.setAddress(resultSet.getString("address"));
        parkingEntity.setSquare(resultSet.getInt("square"));
        return parkingEntity;
    }

    @Override
    public List<ParkingEntity> mapListResult(ResultSet resultSet) throws SQLException {
        List<ParkingEntity> parkingEntities = new ArrayList<>();
        while (resultSet.next()) {
            parkingEntities.add(mapOneResult(resultSet));
        }
        return parkingEntities;
    }
}
