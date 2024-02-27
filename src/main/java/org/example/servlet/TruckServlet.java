package org.example.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.service.TruckService;
import org.example.service.impl.TruckServiceImpl;
import org.example.servlet.dto.TruckDto;
import org.example.servlet.mapper.TruckDtoMapperImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "TruckServlet", value = "/truck")
public class TruckServlet extends HttpServlet {
    private TruckService service;
    private ObjectMapper jsonMapper;

    public TruckServlet() {
        service = new TruckServiceImpl();
        jsonMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id == null) {
            List<TruckDto> result = TruckDtoMapperImpl.entityToDto(service.findAll());
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write(jsonMapper.writeValueAsString(result));
            //System.out.println(jsonMapper.writeValueAsString(result));
        } else {
            TruckDto result = TruckDtoMapperImpl.entityToDto(service.findById(Integer.parseInt(id)));
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
        TruckDto truckDto = jsonMapper.readValue(requestBody.toString(), TruckDto.class);
        TruckDto saveDto = TruckDtoMapperImpl.entityToDto(service.save(TruckDtoMapperImpl.dtoToEntity(truckDto)));
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
        TruckDto truckDto = jsonMapper.readValue(requestBody.toString(), TruckDto.class);
        TruckDto updatedDto = TruckDtoMapperImpl.entityToDto(service.save(TruckDtoMapperImpl.dtoToEntity(truckDto)));
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
