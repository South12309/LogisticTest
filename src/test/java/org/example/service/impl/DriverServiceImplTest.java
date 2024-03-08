package org.example.service.impl;

import org.example.model.DriverEntity;
import org.example.repository.DriverEntityRepository;
import org.example.repository.impl.DriverEntityRepositoryImpl;
import org.example.service.DriverService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DriverServiceImplTest {
    private static DriverEntityRepository repository;
    private static DriverService service;

    @BeforeAll
    static void beforeAll() {
        repository = mock(DriverEntityRepositoryImpl.class);
        service = new DriverServiceImpl(repository);
    }

    @Test
    void save() {
        DriverEntity driverEntity = mock(DriverEntity.class);
        when(repository.findById(driverEntity.getId())).thenReturn(Optional.of(driverEntity));
        when(repository.update(driverEntity)).thenReturn(driverEntity);
        service.save(driverEntity);
        verify(repository, times(1)).update(driverEntity);
        when(repository.findById(driverEntity.getId())).thenReturn(Optional.empty());
        when(repository.save(driverEntity)).thenReturn(driverEntity);
        service.save(driverEntity);
        verify(repository, times(1)).save(driverEntity);

    }

    @Test
    void findById() {
        DriverEntity driverEntity = mock(DriverEntity.class);
        when(repository.findById(anyInt())).thenReturn(Optional.ofNullable(driverEntity));
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
        List<DriverEntity> drivers = mock(List.class);
        when(repository.findAll()).thenReturn(Optional.ofNullable(drivers));
        service.findAll();
        verify(repository, times(1)).findAll();
    }
}