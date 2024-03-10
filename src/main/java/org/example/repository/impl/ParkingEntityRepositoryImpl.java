package org.example.repository.impl;

import org.example.db.ConnectionManagerImpl;
import org.example.model.ParkingEntity;
import org.example.model.TruckEntity;
import org.example.repository.ParkingEntityRepository;
import org.example.repository.TruckEntityRepository;
import org.example.repository.mapper.ParkingResultSetMapper;
import org.example.repository.mapper.ParkingResultSetMapperImpl;
import java.sql.*;
import java.util.List;
import java.util.Optional;

public class ParkingEntityRepositoryImpl implements ParkingEntityRepository {
    private final ParkingResultSetMapper resultSetMapper;
    private final TruckEntityRepository truckEntityRepository;
    private static ParkingEntityRepository INSTANCE;
    public static ParkingEntityRepository getINSTANCE() {
        if (INSTANCE==null) {
            INSTANCE = new ParkingEntityRepositoryImpl();
        }
        return INSTANCE;
    }

    public ParkingEntityRepositoryImpl() {
        resultSetMapper = ParkingResultSetMapperImpl.getINSTANCE();
        truckEntityRepository = TruckEntityRepositoryImpl.getINSTANCE();
    }

    @Override
    public Optional<ParkingEntity> findById(Integer id) {
        try (Connection connection = ConnectionManagerImpl.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM logistic.parkings where id=?", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            ParkingEntity parkingEntity = resultSetMapper.mapOneResult(resultSet);
            parkingEntity.setTrucks(truckEntityRepository.findByParkingId(parkingEntity.getId()));
            return Optional.ofNullable(parkingEntity);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Optional<List<ParkingEntity>> findAll() {
        try (Connection connection = ConnectionManagerImpl.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM logistic.parkings");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<ParkingEntity> parkingEntities = resultSetMapper.mapListResult(resultSet);
            for (ParkingEntity parkingEntity : parkingEntities) {
                List<TruckEntity> trucksByParkingId = truckEntityRepository.findByParkingId(parkingEntity.getId());
                parkingEntity.setTrucks(trucksByParkingId);
            }
            return Optional.ofNullable(parkingEntities);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public boolean deleteById(Integer id) {
        try (Connection connection = ConnectionManagerImpl.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM logistic.parkings where id=?");
            preparedStatement.setInt(1, id);
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ParkingEntity save(ParkingEntity parkingEntity) {
        try (Connection connection = ConnectionManagerImpl.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO logistic.parkings (address, square) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, parkingEntity.getAddress());
            preparedStatement.setInt(2, parkingEntity.getSquare());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                parkingEntity.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Error insert");
            }
            return parkingEntity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public ParkingEntity update(ParkingEntity parkingEntity) {
        try (Connection connection = ConnectionManagerImpl.getInstance().getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "UPDATE logistic.parkings SET address = ?, square = ? WHERE id = ?");
            preparedStatement.setObject(1, parkingEntity.getAddress());
            preparedStatement.setObject(2,parkingEntity.getSquare());
            preparedStatement.setObject(3,parkingEntity.getId());
            preparedStatement.executeUpdate();
            return parkingEntity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
