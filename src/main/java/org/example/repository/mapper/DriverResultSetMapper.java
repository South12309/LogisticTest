package org.example.repository.mapper;

import org.example.model.DriverEntity;

import java.sql.ResultSet;

public interface DriverResultSetMapper {
    DriverEntity map(ResultSet resultSet);
}
