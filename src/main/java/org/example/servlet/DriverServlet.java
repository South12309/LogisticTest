package org.example.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.DriverEntity;
import org.example.service.DriverService;
import org.example.service.impl.DriverServiceImpl;
import org.example.servlet.dto.DriverDto;
import org.example.servlet.mapper.DriverDtoMapperImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "DriverServlet", value = "/driver")
public class DriverServlet extends HttpServlet {
    private DriverService service;
    private ObjectMapper jsonMapper;

    public DriverServlet() {
        service = new DriverServiceImpl();
        jsonMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id == null) {
            List<DriverDto> result = DriverDtoMapperImpl.entityToDto(service.findAll());
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write(jsonMapper.writeValueAsString(result));
        } else {
            DriverDto result = DriverDtoMapperImpl.entityToDto(service.findById(Integer.parseInt(id)));
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write(jsonMapper.writeValueAsString(result));
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder requestBody = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }
        DriverDto driverDTO = jsonMapper.readValue(requestBody.toString(), DriverDto.class);
        DriverDto saveDto = DriverDtoMapperImpl.entityToDto(service.save(DriverDtoMapperImpl.dtoToEntity(driverDTO)));
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().write(jsonMapper.writeValueAsString(saveDto));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder requestBody = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }
        DriverDto driverDTO = jsonMapper.readValue(requestBody.toString(), DriverDto.class);
        DriverDto updatedDto = DriverDtoMapperImpl.entityToDto(service.save(DriverDtoMapperImpl.dtoToEntity(driverDTO)));
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().write(jsonMapper.writeValueAsString(updatedDto));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        if (!service.delete(id)) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else {
            resp.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
