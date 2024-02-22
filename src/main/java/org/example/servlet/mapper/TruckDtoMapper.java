package org.example.servlet.mapper;

import org.example.model.DriverEntity;
import org.example.model.TruckEntity;
import org.example.servlet.dto.DriverDto;
import org.example.servlet.dto.TruckDto;

import java.util.List;

public interface TruckDtoMapper {
    TruckEntity dtoToEntity(TruckDto dto);
    TruckDto entityToDto(TruckEntity entity);
    List<TruckDto> entityToDto(List<TruckEntity> entities);
    List<TruckEntity> dtoToEntity(List<TruckDto> dtos);
}
