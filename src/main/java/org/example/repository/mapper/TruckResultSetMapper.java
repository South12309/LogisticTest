package org.example.repository.mapper;

import org.example.model.TruckEntity;

import java.sql.ResultSet;

public interface TruckResultSetMapper {
    TruckEntity map(ResultSet resultSet);
}
