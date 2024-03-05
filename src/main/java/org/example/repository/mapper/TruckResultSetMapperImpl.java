package org.example.repository.mapper;

import org.example.model.DriverEntity;
import org.example.model.ParkingEntity;
import org.example.model.TruckEntity;
import org.example.repository.ParkingEntityRepository;
import org.example.repository.impl.ParkingEntityRepositoryImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TruckResultSetMapperImpl implements TruckResultSetMapper {
    private static TruckResultSetMapper INSTANCE;

    public static TruckResultSetMapper getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new TruckResultSetMapperImpl();
        }
        return INSTANCE;
    }

    private TruckResultSetMapperImpl() {

    }

    @Override
    public TruckEntity mapOneResult(ResultSet resultSet) throws SQLException {
        if (resultSet.isBeforeFirst()) {
            resultSet.next();
        }
        TruckEntity truckEntity = new TruckEntity();
        truckEntity.setId(resultSet.getInt("id"));
        truckEntity.setModel(resultSet.getString("model"));
        truckEntity.setNumber(resultSet.getString("number"));
        ParkingEntity parking = new ParkingEntity();
        parking.setId(resultSet.getInt("parking_id"));
        truckEntity.setParking(parking);
        return truckEntity;
    }

    @Override
    public List<TruckEntity> mapListResult(ResultSet resultSet) throws SQLException {
        List<TruckEntity> truckEntities = new ArrayList<>();
        while (resultSet.next()) {
            truckEntities.add(mapOneResult(resultSet));
        }
        return truckEntities;
    }
}
