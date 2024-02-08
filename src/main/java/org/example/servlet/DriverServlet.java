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
import java.util.List;
import java.util.UUID;


@WebServlet(name = "DriverServlet", value = "/driver")
public class DriverServlet extends HttpServlet {
    private DriverService service;
    private DriverDtoMapper dtomapper;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id==null) {
            List<DriverDto> result = dtomapper.entityToDto(service.findAll());
        } else {
            DriverDto result = dtomapper.entityToDto(service.findById(Integer.parseInt(id)));
        }
        //TODO
        // return our result in json
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DriverEntity driverEntity = dtomapper.map(new DriverDto());
        DriverEntity saved = service.save(driverEntity);
        DriverDto map = dtomapper.map(saved);
        // return our DTO, not necessary
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DriverEntity driverEntity = dtomapper.map(new DriverDto());
        DriverEntity updated = service.update(driverEntity);
        DriverDto map = dtomapper.map(updated);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DriverEntity driverEntity = dtomapper.map(new DriverDto());
        Boolean isDeleted = service.delete(driverEntity);
    }
}
