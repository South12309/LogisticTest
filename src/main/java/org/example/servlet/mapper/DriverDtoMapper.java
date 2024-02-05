package org.example.servlet.mapper;

import org.example.model.DriverEntity;
import org.example.servlet.dto.DriverDto;

public interface DriverDtoMapper {
    DriverEntity map(DriverDto driverDto);

    DriverDto map(DriverEntity driverEntity);
}
