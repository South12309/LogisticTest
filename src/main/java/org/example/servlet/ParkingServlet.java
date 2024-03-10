package org.example.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
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

    private final transient ParkingService service;
    private final ObjectMapper jsonMapper;

    public ParkingServlet() {
        service = new ParkingServiceImpl();
        jsonMapper = new ObjectMapper();
    }

    public ParkingServlet(ParkingService service) {
        this.service = service;
        jsonMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String id = req.getParameter("id");
            if (id == null) {
                List<ParkingDto> result = ParkingDtoMapperImpl.entityToDto(service.findAll());
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write(jsonMapper.writeValueAsString(result));
            } else {
                ParkingDto result = ParkingDtoMapperImpl.entityToDto(service.findById(Integer.parseInt(id)));
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write(jsonMapper.writeValueAsString(result));
            }
        } catch (IOException e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        saveDto(req, resp);
    }

    private void saveDto(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
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
        } catch (IOException e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        saveDto(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Integer id = Integer.parseInt(req.getParameter("id"));
            if (!service.delete(id)) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            } else {
                resp.setStatus(HttpServletResponse.SC_OK);
            }
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
