package org.example.servlet.mapper;

import org.example.model.TruckEntity;
import org.example.servlet.dto.TruckDto;

import java.util.ArrayList;
import java.util.List;

public class TruckDtoMapperImpl implements TruckDtoMapper {
    private DriverDtoMapper driverDtoMapper;

    public TruckDtoMapperImpl() {
        driverDtoMapper = new DriverDtoMapperImpl();
    }

    @Override
    public TruckEntity dtoToEntity(TruckDto dto) {
        TruckEntity truckEntity = new TruckEntity();
        truckEntity.setId(dto.getId());
        truckEntity.setModel(dto.getModel());
        truckEntity.setNumber(dto.getNumber());
        truckEntity.setParkingId(dto.getParkingId());
        truckEntity.setDrivers(driverDtoMapper.dtoToEntity(dto.getDrivers()));
        return truckEntity;
    }

    @Override
    public TruckDto entityToDto(TruckEntity entity) {
        TruckDto truckDto = new TruckDto();
        truckDto.setId(entity.getId());
        truckDto.setModel(entity.getModel());
        truckDto.setNumber(entity.getNumber());
        truckDto.setParkingId(entity.getParkingId());
        truckDto.setDrivers(driverDtoMapper.entityToDto(entity.getDrivers()));
        return truckDto;
    }

    @Override
    public List<TruckDto> entityToDto(List<TruckEntity> entities) {
        List<TruckDto> truckDtos = new ArrayList<>();
        for (TruckEntity entity : entities) {
            truckDtos.add(entityToDto(entity));
        }
        return truckDtos;
    }

    @Override
    public List<TruckEntity> dtoToEntity(List<TruckDto> dtos) {
        List<TruckEntity> truckEntities = new ArrayList<>();
        for (TruckDto dto : dtos) {
            truckEntities.add(dtoToEntity(dto));
        }
        return truckEntities;
    }
}
