package org.example.repository.mapper;

import org.example.model.DriverEntity;
import org.example.model.TruckEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TruckResultSetMapperImpl implements TruckResultSetMapper {

    @Override
    public TruckEntity mapOneResult(ResultSet resultSet) throws SQLException {
        TruckEntity truckEntity = new TruckEntity();
        truckEntity.setId(resultSet.getInt("id"));
        truckEntity.setModel(resultSet.getString("model"));
        truckEntity.setNumber(resultSet.getString("number"));
        truckEntity.setParkingId(resultSet.getInt("parking_id"));
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
