package org.example.repository.mapper;

import org.example.model.DriverEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DriverResultSetMapper {
    List<DriverEntity> mapListResult(ResultSet resultSet) throws SQLException;

    DriverEntity mapOneResult(ResultSet resultSet) throws SQLException;
}
