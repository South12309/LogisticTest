package org.example.repository.mapper;

import org.example.model.DriverEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DriverTruckResultSetMapperImpl implements DriverResultSetMapper {

    @Override
    public List<DriverEntity> mapListResult(ResultSet resultSet) throws SQLException {
        return null;
    }


    @Override
    public DriverEntity mapOneResult(ResultSet resultSet) {

        return null;
    }
}
