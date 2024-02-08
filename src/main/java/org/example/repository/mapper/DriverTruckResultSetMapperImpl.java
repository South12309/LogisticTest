package org.example.repository.mapper;

import org.example.model.DriverEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DriverTruckResultSetMapperImpl implements DriverResultSetMapper {

    @Override
    public List<DriverEntity> mapListResult(ResultSet resultSet) {
        List<DriverEntity> resultDriverList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                resultDriverList.add(new DriverEntity(resultSet.getInt("id"), resultSet.getString("fio"), ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



    @Override
    public DriverEntity mapOneResult(ResultSet resultSet) {

        return null;
    }
}
