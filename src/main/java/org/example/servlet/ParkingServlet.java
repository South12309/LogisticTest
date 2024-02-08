package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.DriverEntity;
import org.example.model.ParkingEntity;
import org.example.service.DriverService;
import org.example.service.ParkingService;
import org.example.servlet.dto.DriverDto;
import org.example.servlet.dto.ParkingDto;
import org.example.servlet.mapper.DriverDtoMapper;
import org.example.servlet.mapper.ParkingDtoMapper;

import java.io.IOException;
import java.util.UUID;


@WebServlet(name = "ParkingServlet", value = "/parking")
public class ParkingServlet extends HttpServlet {
    private ParkingService service;
    private ParkingDtoMapper dtomapper;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.randomUUID();// Our Id from request
        ParkingEntity byId = service.findById(uuid);
        ParkingDto outGoingDto = dtomapper.map(byId);
        // return our DTO
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ParkingEntity simpleEntity = dtomapper.map(new ParkingDto());
        ParkingEntity saved = service.save(simpleEntity);
        ParkingDto map = dtomapper.map(saved);
        // return our DTO, not necessary
    }
}
