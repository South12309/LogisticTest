package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.DriverEntity;
import org.example.service.DriverService;
import org.example.servlet.dto.DriverDto;
import org.example.servlet.dto.TruckDto;
import org.example.servlet.mapper.DriverDtoMapper;

import java.io.IOException;
import java.util.UUID;


@WebServlet(name = "DriverServlet", value = "/driver")
public class DriverServlet extends HttpServlet {
    private DriverService service;
    private DriverDtoMapper dtomapper;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.randomUUID();// Our Id from request
        DriverEntity byId = service.findById(uuid);
        DriverDto outGoingDto = dtomapper.map(byId);
        // return our DTO
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DriverEntity simpleEntity = dtomapper.map(new DriverDto());
        DriverEntity saved = service.save(simpleEntity);
        DriverDto map = dtomapper.map(saved);
        // return our DTO, not necessary
    }
}
