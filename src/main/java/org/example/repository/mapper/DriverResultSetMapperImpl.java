package org.example.repository.mapper;

import org.example.model.DriverEntity;
import org.example.repository.TruckEntityRepository;
import org.example.repository.impl.TruckEntityRepositoryImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DriverResultSetMapperImpl implements DriverResultSetMapper {
    private static DriverResultSetMapper INSTANCE;

    public static DriverResultSetMapper getINSTANCE() {
        if (INSTANCE==null) {
            INSTANCE = new DriverResultSetMapperImpl();
        }
        return INSTANCE;
    }
    private DriverResultSetMapperImpl() {

    }

    @Override
    public List<DriverEntity> mapListResult(ResultSet resultSet) throws SQLException {
        List<DriverEntity> resultDriverList = new ArrayList<>();
        while (resultSet.next()) {
            resultDriverList.add(mapOneResult(resultSet));
        }
        return resultDriverList;
    }
    @Override
    public DriverEntity mapOneResult(ResultSet resultSet) throws SQLException {
        DriverEntity driverEntity = new DriverEntity();
        driverEntity.setId(resultSet.getInt("id"));
        driverEntity.setFio(resultSet.getString("fio"));
        return driverEntity;
    }
}
