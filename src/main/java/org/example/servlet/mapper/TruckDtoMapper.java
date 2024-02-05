package org.example.servlet.mapper;

import org.example.model.TruckEntity;
import org.example.servlet.dto.TruckDto;

public interface TruckDtoMapper {
    TruckEntity map(TruckDto truckDto);

    TruckDto map(TruckEntity truckEntity);
}
