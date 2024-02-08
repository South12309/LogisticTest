package org.example.repository.mapper;

import org.example.model.TruckEntity;

import java.sql.ResultSet;
import java.util.List;

public class TruckResultSetMapperImpl implements TruckResultSetMapper {

    @Override
    public TruckEntity mapOneResult(ResultSet resultSet) {
        return null;
    }

    @Override
    public List<TruckEntity> mapListResult(ResultSet resultSet) {
        return null;
    }
}
