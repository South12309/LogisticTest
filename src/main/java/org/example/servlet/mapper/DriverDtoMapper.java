package org.example.servlet.mapper;

import org.example.model.DriverEntity;
import org.example.servlet.dto.DriverDto;

import java.util.List;

public interface DriverDtoMapper {
    DriverDto entityToDto(DriverEntity entity);
    List<DriverDto> entityToDto(List<DriverEntity> entities);

    DriverEntity dtoToEntity(DriverDto dto);
    List<DriverEntity> dtoToEntity(List<DriverDto> dtos);
}
