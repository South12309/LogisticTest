package org.example.service.impl;

import org.example.model.DriverEntity;
import org.example.model.ParkingEntity;
import org.example.repository.DriverEntityRepository;
import org.example.repository.ParkingEntityRepository;
import org.example.repository.impl.DriverEntityRepositoryImpl;
import org.example.repository.impl.ParkingEntityRepositoryImpl;
import org.example.service.DriverService;
import org.example.service.ParkingService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class ParkingServiceImplTest {
    private static ParkingEntityRepository repository;
    private static ParkingService service;
    @BeforeAll
    static void beforeAll() {
        repository = mock(ParkingEntityRepositoryImpl.class);
        service = new ParkingServiceImpl(repository);
    }

    @Test
    void save() {
        ParkingEntity parkingEntity = mock(ParkingEntity.class);
        when(repository.findById(parkingEntity.getId())).thenReturn(Optional.of(parkingEntity));
        when(repository.update(parkingEntity)).thenReturn(parkingEntity);
        service.save(parkingEntity);
        verify(repository, times(1)).update(parkingEntity);
        when(repository.findById(parkingEntity.getId())).thenReturn(Optional.empty());
        when(repository.save(parkingEntity)).thenReturn(parkingEntity);
        service.save(parkingEntity);
        verify(repository, times(1)).save(parkingEntity);
    }

    @Test
    void findById() {
        ParkingEntity parkingEntity = mock(ParkingEntity.class);
        when(repository.findById(anyInt())).thenReturn(Optional.ofNullable(parkingEntity));
        service.findById(anyInt());
        verify(repository, times(1)).findById(anyInt());
    }

    @Test
    void delete() {
        when(repository.deleteById(anyInt())).thenReturn(true);
        service.delete(anyInt());
        verify(repository, times(1)).deleteById(anyInt());
    }

    @Test
    void findAll() {
        List<ParkingEntity> parkings = mock(List.class);
        when(repository.findAll()).thenReturn(Optional.ofNullable(parkings));
        service.findAll();
        verify(repository, times(1)).findAll();
    }
}