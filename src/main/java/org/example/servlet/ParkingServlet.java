package org.example.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.service.ParkingService;
import org.example.service.impl.ParkingServiceImpl;
import org.example.servlet.dto.ParkingDto;
import org.example.servlet.mapper.ParkingDtoMapperImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "ParkingServlet", value = "/parking")
public class ParkingServlet extends HttpServlet {
    private ParkingService service;
    private ObjectMapper jsonMapper;

    public ParkingServlet() {
        service = new ParkingServiceImpl();
        jsonMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id == null) {
            List<ParkingDto> result = ParkingDtoMapperImpl.entityToDto(service.findAll());
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write(jsonMapper.writeValueAsString(result));
            //System.out.println(jsonMapper.writeValueAsString(result));
        } else {
            ParkingDto result = ParkingDtoMapperImpl.entityToDto(service.findById(Integer.parseInt(id)));
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write(jsonMapper.writeValueAsString(result));
            //System.out.println(jsonMapper.writeValueAsString(result));
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
        ParkingDto parkingDto = jsonMapper.readValue(requestBody.toString(), ParkingDto.class);
        ParkingDto saveDto = ParkingDtoMapperImpl.entityToDto(service.save(ParkingDtoMapperImpl.dtoToEntity(parkingDto)));
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
        ParkingDto parkingDto = jsonMapper.readValue(requestBody.toString(), ParkingDto.class);
        ParkingDto updatedDto = ParkingDtoMapperImpl.entityToDto(service.save(ParkingDtoMapperImpl.dtoToEntity(parkingDto)));
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
