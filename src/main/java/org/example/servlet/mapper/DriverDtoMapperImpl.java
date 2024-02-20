package org.example.servlet.mapper;

import org.example.model.DriverEntity;
import org.example.servlet.dto.DriverDto;

import java.util.ArrayList;
import java.util.List;

public class DriverDtoMapperImpl implements DriverDtoMapper {
    @Override
    public DriverDto entityToDto(DriverEntity entity) {
        DriverDto driverDto = new DriverDto();
        driverDto.setId(entity.getId());
        driverDto.setFio(entity.getFio());
        driverDto.setTrucks(entity.getTrucks());
        return driverDto;
    }

    @Override
    public List<DriverDto> entityToDto(List<DriverEntity> entities) {
        List<DriverDto> driverDtos = new ArrayList<>();
        for (DriverEntity entity : entities) {
            driverDtos.add(entityToDto(entity));
        }
        return driverDtos;
    }

    @Override
    public DriverEntity dtoToEntity(DriverDto dto) {
        DriverEntity driverEntity = new DriverEntity();
        driverEntity.setId(dto.getId());
        driverEntity.setFio(dto.getFio());
        driverEntity.setTrucks(dto.getTrucks());
        return driverEntity;
    }

    @Override
    public List<DriverEntity> dtoToEntity(List<DriverDto> dtos) {
        List<DriverEntity> driverEntities = new ArrayList<>();
        for (DriverDto dto : dtos) {
            driverEntities.add(dtoToEntity(dto));
        }
        return driverEntities;
    }
}
