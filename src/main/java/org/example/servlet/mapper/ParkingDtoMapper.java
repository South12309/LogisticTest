package org.example.servlet.mapper;

import org.example.model.DriverEntity;
import org.example.model.ParkingEntity;
import org.example.servlet.dto.DriverDto;
import org.example.servlet.dto.ParkingDto;

public interface ParkingDtoMapper {
    ParkingEntity map(ParkingDto parkingDto);

    ParkingDto map(ParkingEntity parkingEntity);
}
