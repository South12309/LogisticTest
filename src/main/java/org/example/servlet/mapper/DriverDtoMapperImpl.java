package org.example.servlet.mapper;

import org.example.model.DriverEntity;
import org.example.servlet.dto.DriverDto;

import java.util.ArrayList;
import java.util.List;

public class DriverDtoMapperImpl {

    private DriverDtoMapperImpl() {
    }

    public static DriverDto entityToDto(DriverEntity entity) {
        if (entity==null)
            return null;
        DriverDto driverDto = new DriverDto();
        driverDto.setId(entity.getId());
        driverDto.setSurname(entity.getSurname());
        driverDto.setName(entity.getName());
        driverDto.setPatronymic(entity.getPatronymic());
        driverDto.setTrucks(TruckDtoMapperImpl.entityToDto(entity.getTrucks()));
        return driverDto;
    }

    public static List<DriverDto> entityToDto(List<DriverEntity> entities) {
        if (entities==null)
            return new ArrayList<>();
        List<DriverDto> driverDtos = new ArrayList<>();
        for (DriverEntity entity : entities) {
            driverDtos.add(entityToDto(entity));
        }
        return driverDtos;
    }

    public static DriverEntity dtoToEntity(DriverDto dto) {
        if (dto==null)
            return null;
        DriverEntity driverEntity = new DriverEntity();
        driverEntity.setId(dto.getId());
        driverEntity.setSurname(dto.getSurname());
        driverEntity.setName(dto.getName());
        driverEntity.setPatronymic(dto.getPatronymic());
        driverEntity.setTrucks(TruckDtoMapperImpl.dtoToEntity(dto.getTrucks()));
        return driverEntity;
    }

    public static  List<DriverEntity> dtoToEntity(List<DriverDto> dtos) {
        if (dtos==null)
            return new ArrayList<>();
        List<DriverEntity> driverEntities = new ArrayList<>();
        for (DriverDto dto : dtos) {
            driverEntities.add(dtoToEntity(dto));
        }
        return driverEntities;
    }
}
