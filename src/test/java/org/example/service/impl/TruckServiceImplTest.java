package org.example.service.impl;

import org.example.model.ParkingEntity;
import org.example.model.TruckEntity;
import org.example.repository.ParkingEntityRepository;
import org.example.repository.TruckEntityRepository;
import org.example.repository.impl.ParkingEntityRepositoryImpl;
import org.example.repository.impl.TruckEntityRepositoryImpl;
import org.example.service.ParkingService;
import org.example.service.TruckService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class TruckServiceImplTest {
    private static TruckEntityRepository repository;
    private static TruckService service;
    @BeforeAll
    static void beforeAll() {
        repository = mock(TruckEntityRepositoryImpl.class);
        service = new TruckServiceImpl(repository);
    }

    @Test
    void save() {
        TruckEntity truckEntity = mock(TruckEntity.class);
        when(repository.findById(truckEntity.getId())).thenReturn(Optional.of(truckEntity));
        when(repository.update(truckEntity)).thenReturn(truckEntity);
        service.save(truckEntity);
        verify(repository, times(1)).update(truckEntity);
        when(repository.findById(truckEntity.getId())).thenReturn(Optional.empty());
        when(repository.save(truckEntity)).thenReturn(truckEntity);
        service.save(truckEntity);
        verify(repository, times(1)).save(truckEntity);
    }

    @Test
    void findById() {
        TruckEntity truckEntity = mock(TruckEntity.class);
        when(repository.findById(anyInt())).thenReturn(Optional.ofNullable(truckEntity));
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
        List<TruckEntity> trucks = mock(List.class);
        when(repository.findAll()).thenReturn(Optional.ofNullable(trucks));
        service.findAll();
        verify(repository, times(1)).findAll();
    }
}