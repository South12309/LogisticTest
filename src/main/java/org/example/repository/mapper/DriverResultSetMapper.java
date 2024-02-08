package org.example.repository.mapper;

import org.example.model.DriverEntity;

import java.sql.ResultSet;
import java.util.List;

public interface DriverResultSetMapper {
    List<DriverEntity> mapListResult(ResultSet resultSet);

    DriverEntity mapOneResult(ResultSet resultSet);
}
