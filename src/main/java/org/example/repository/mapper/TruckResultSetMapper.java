package org.example.repository.mapper;

import org.example.model.TruckEntity;

import java.sql.ResultSet;
import java.util.List;

public interface TruckResultSetMapper {
    TruckEntity mapOneResult(ResultSet resultSet);
    List<TruckEntity> mapListResult(ResultSet resultSet);
}
