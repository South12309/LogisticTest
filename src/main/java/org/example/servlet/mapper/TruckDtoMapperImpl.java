package org.example.servlet.mapper;

import org.example.model.TruckEntity;
import org.example.servlet.dto.TruckDto;

import java.util.ArrayList;
import java.util.List;

public class TruckDtoMapperImpl {

    private TruckDtoMapperImpl() {
    }
    public static TruckEntity dtoToEntity(TruckDto dto) {
        if (dto==null)
            return null;
        TruckEntity truckEntity = new TruckEntity();
        truckEntity.setId(dto.getId());
        truckEntity.setModel(dto.getModel());
        truckEntity.setNumber(dto.getNumber());
        truckEntity.setParking(ParkingDtoMapperImpl.dtoToEntity(dto.getParking()));
        truckEntity.setDrivers(DriverDtoMapperImpl.dtoToEntity(dto.getDrivers()));
        return truckEntity;
    }
    public static  TruckDto entityToDto(TruckEntity entity) {
        if (entity==null)
            return null;
        TruckDto truckDto = new TruckDto();
        truckDto.setId(entity.getId());
        truckDto.setModel(entity.getModel());
        truckDto.setNumber(entity.getNumber());
        truckDto.setParking(ParkingDtoMapperImpl.entityToDto(entity.getParking()));
        truckDto.setDrivers(DriverDtoMapperImpl.entityToDto(entity.getDrivers()));
        return truckDto;
    }
    public static List<TruckDto> entityToDto(List<TruckEntity> entities) {
        if (entities==null)
            return null;
        List<TruckDto> truckDtos = new ArrayList<>();
        for (TruckEntity entity : entities) {
            truckDtos.add(entityToDto(entity));
        }
        return truckDtos;
    }
    public static List<TruckEntity> dtoToEntity(List<TruckDto> dtos) {
        if (dtos==null)
            return null;
        List<TruckEntity> truckEntities = new ArrayList<>();
        for (TruckDto dto : dtos) {
            truckEntities.add(dtoToEntity(dto));
        }
        return truckEntities;
    }
}
