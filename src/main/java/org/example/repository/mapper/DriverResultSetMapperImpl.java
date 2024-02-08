package org.example.repository.mapper;

import org.example.model.DriverEntity;

import java.sql.ResultSet;
import java.util.List;

public class DriverResultSetMapperImpl implements DriverResultSetMapper {

    @Override
    public List<DriverEntity> mapListResult(ResultSet resultSet) {
        return null;
    }

    @Override
    public DriverEntity mapOneResult(ResultSet resultSet) {
        return null;
    }
}
