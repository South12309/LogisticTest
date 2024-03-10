package org.example.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.service.DriverService;
import org.example.service.impl.DriverServiceImpl;
import org.example.servlet.dto.DriverDto;
import org.example.servlet.mapper.DriverDtoMapperImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "DriverServlet", value = "/driver")
public class DriverServlet extends HttpServlet {
    private final transient DriverService service;
    private final ObjectMapper jsonMapper;

    public DriverServlet() {
        service = new DriverServiceImpl();
        jsonMapper = new ObjectMapper();
    }

    public DriverServlet(DriverService service) {
        this.service = service;
        jsonMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
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
        } catch (IOException e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        saveDto(req, resp);
    }

    public void saveDto(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
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
