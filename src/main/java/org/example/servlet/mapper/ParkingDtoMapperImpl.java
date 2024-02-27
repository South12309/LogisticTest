package org.example.servlet.mapper;

import org.example.model.DriverEntity;
import org.example.model.ParkingEntity;
import org.example.servlet.dto.DriverDto;
import org.example.servlet.dto.ParkingDto;

import java.util.ArrayList;
import java.util.List;

public class ParkingDtoMapperImpl {
    private ParkingDtoMapperImpl() {
    }

    public static ParkingDto entityToDto(ParkingEntity entity) {
        ParkingDto parkingDto = new ParkingDto();
        parkingDto.setId(entity.getId());
        parkingDto.setAddress(entity.getAddress());
        parkingDto.setSquare(entity.getSquare());
        parkingDto.setTrucks(TruckDtoMapperImpl.entityToDto(entity.getTrucks()));
        return parkingDto;
    }

    public static List<ParkingDto> entityToDto(List<ParkingEntity> entities) {
        List<ParkingDto> parkingDtos = new ArrayList<>();
        for (ParkingEntity entity : entities) {
            parkingDtos.add(entityToDto(entity));
        }
        return parkingDtos;
    }

    public static ParkingEntity dtoToEntity(ParkingDto dto) {
        ParkingEntity parkingEntity = new ParkingEntity();
        parkingEntity.setId(dto.getId());
        parkingEntity.setAddress(dto.getAddress());
        parkingEntity.setSquare(dto.getSquare());
        parkingEntity.setTrucks(TruckDtoMapperImpl.dtoToEntity(dto.getTrucks()));
        return parkingEntity;
    }

    public static List<ParkingEntity> dtoToEntity(List<ParkingDto> dtos) {
        List<ParkingEntity> parkingEntities = new ArrayList<>();
        for (ParkingDto dto : dtos) {
            parkingEntities.add(dtoToEntity(dto));
        }
        return parkingEntities;
    }
}
