package org.example.repository.impl;

import org.example.db.ConnectionManager;
import org.example.db.ConnectionManagerImpl;
import org.example.model.DriverEntity;
import org.example.model.TruckEntity;
import org.example.repository.DriverEntityRepository;
import org.example.repository.DriverTruckEntityRepository;
import org.example.repository.mapper.DriverResultSetMapper;
import org.example.repository.mapper.DriverResultSetMapperImpl;
import org.example.repository.mapper.TruckResultSetMapper;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class DriverEntityRepositoryImpl implements DriverEntityRepository {
    private DriverTruckEntityRepository driverTruckEntityRepository;
    private DriverResultSetMapper driverResultSetMapper;
    private static DriverEntityRepository INSTANCE;

    public static DriverEntityRepository getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new DriverEntityRepositoryImpl();
        }
        return INSTANCE;
    }

    private DriverEntityRepositoryImpl() {
        driverTruckEntityRepository = DriverTruckEntityRepositoryImpl.getINSTANCE();
        driverResultSetMapper = DriverResultSetMapperImpl.getINSTANCE();
    }

    @Override
    public Optional<DriverEntity> findById(Integer id) {
        try (Connection connection = ConnectionManagerImpl.getInstance().getConnection()) {
            PreparedStatement preparedStatementDrivers = connection.prepareStatement("SELECT * FROM logistic.drivers where id=?");
            preparedStatementDrivers.setInt(1, id);
            ResultSet resultSetDrivers = preparedStatementDrivers.executeQuery();
            DriverEntity driverEntity = driverResultSetMapper.mapOneResult(resultSetDrivers);
            driverEntity.setTrucks(driverTruckEntityRepository.findTrucksByDriverId(id));

            return Optional.ofNullable(driverEntity);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<List<DriverEntity>> findAll() {
        ConnectionManagerImpl instance = ConnectionManagerImpl.getInstance();
        try (Connection connection = instance.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM logistic.drivers");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<DriverEntity> driverEntities = driverResultSetMapper.mapListResult(resultSet);
            for (DriverEntity driverEntity : driverEntities) {
                List<TruckEntity> trucksByDriverId = driverTruckEntityRepository.findTrucksByDriverId(driverEntity.getId());
                driverEntity.setTrucks(trucksByDriverId);
            }
            return Optional.ofNullable(driverEntities);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        try (Connection connection = ConnectionManagerImpl.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM logistic.drivers where id=?");
            preparedStatement.setInt(1, id);
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public DriverEntity save(DriverEntity driverEntity) {
        try (Connection connection = ConnectionManagerImpl.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO logistic.drivers (surname, name, patronymic) VALUES(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, driverEntity.getSurname());
            preparedStatement.setString(2, driverEntity.getName());
            preparedStatement.setString(3, driverEntity.getPatronymic());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                driverEntity.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Error insert");
            }
            return driverEntity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public DriverEntity update(DriverEntity driverEntity) {
        try (Connection connection = ConnectionManagerImpl.getInstance().getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "UPDATE logistic.drivers SET surname = ?, name = ?, patronymic = ? WHERE id = ?");
            preparedStatement.setString(1, driverEntity.getSurname());
            preparedStatement.setString(2, driverEntity.getName());
            preparedStatement.setString(3, driverEntity.getPatronymic());
            preparedStatement.setObject(4, driverEntity.getId());
            preparedStatement.executeUpdate();
            return driverEntity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
