package org.example.servlet.mapper;

import org.example.model.DriverEntity;
import org.example.model.ParkingEntity;
import org.example.servlet.dto.DriverDto;
import org.example.servlet.dto.ParkingDto;

import java.util.List;

public interface ParkingDtoMapper {
    ParkingDto entityToDto(ParkingEntity entity);
    List<ParkingDto> entityToDto(List<ParkingEntity> entities);
    ParkingEntity dtoToEntity(ParkingDto dto);
    List<ParkingEntity> dtoToEntity(List<ParkingDto> dtos);
}
