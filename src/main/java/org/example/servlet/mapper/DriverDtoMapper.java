package org.example.servlet.mapper;

import org.example.model.DriverEntity;
import org.example.servlet.dto.DriverDto;

import java.util.List;

public interface DriverDtoMapper {
    DriverEntity map(DriverDto driverDto);

    DriverDto map(DriverEntity driverEntity);
    DriverDto entityToDto(DriverEntity entity);
    List<DriverDto> entityToDto(List<DriverEntity> all);
}
