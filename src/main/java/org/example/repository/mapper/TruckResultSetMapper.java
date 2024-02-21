package org.example.repository.mapper;

import org.example.model.TruckEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface TruckResultSetMapper {
    TruckEntity mapOneResult(ResultSet resultSet) throws SQLException;
    List<TruckEntity> mapListResult(ResultSet resultSet) throws SQLException;
}
