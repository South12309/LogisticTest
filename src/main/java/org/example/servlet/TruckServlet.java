package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.TruckEntity;
import org.example.service.TruckService;
import org.example.servlet.dto.DriverDto;
import org.example.servlet.dto.TruckDto;
import org.example.servlet.mapper.DriverDtoMapper;
import org.example.servlet.mapper.TruckDtoMapper;

import java.io.IOException;
import java.util.UUID;


@WebServlet(name = "TruckServlet", value = "/truck")
public class TruckServlet extends HttpServlet {
    private TruckService service;
    private TruckDtoMapper dtomapper;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.randomUUID();// Our Id from request
        TruckEntity byId = service.findById(uuid);
        TruckDto outGoingDto = dtomapper.map(byId);
        // return our DTO
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TruckEntity simpleEntity = dtomapper.map(new TruckDto());
        TruckEntity saved = service.save(simpleEntity);
        TruckDto map = dtomapper.map(saved);
        // return our DTO, not necessary
    }
}
