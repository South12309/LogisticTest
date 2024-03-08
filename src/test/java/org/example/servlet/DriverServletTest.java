package org.example.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import org.example.model.DriverEntity;
import org.example.service.DriverService;
import org.example.service.impl.DriverServiceImpl;
import org.example.servlet.dto.DriverDto;
import org.example.servlet.mapper.DriverDtoMapperImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class DriverServletTest {
    private static DriverServlet servlet;
    private static DriverService service;
   // private static ObjectMapper jsonMapper;

    @BeforeAll
    static void beforeAll() {
        service = mock(DriverServiceImpl.class);
        servlet = new DriverServlet(service);
        // jsonMapper = new ObjectMapper();
    }

    @Test
    void doGet() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn("1").thenReturn(null);
        PrintWriter printWriter = mock(PrintWriter.class);
        when(resp.getWriter()).thenReturn(printWriter);

        DriverEntity driverEntity1 = new DriverEntity();
        driverEntity1.setSurname("Surname1");
        driverEntity1.setName("Name1");
        driverEntity1.setPatronymic("Patronymic1");

        when(service.findById(1)).thenReturn(driverEntity1);
        servlet.doGet(req, resp);
        verify(service, times(1)).findById(anyInt());
        verify(resp, times(1)).setStatus(HttpServletResponse.SC_OK);
        verify(resp, times(1)).getWriter();

        DriverEntity driverEntity2 = new DriverEntity();
        driverEntity1.setSurname("Surname2");
        driverEntity1.setName("Name2");
        driverEntity1.setPatronymic("Patronymic2");

        List<DriverEntity> driverEntities = List.of(driverEntity1, driverEntity2);
        when(service.findAll()).thenReturn(driverEntities);
        servlet.doGet(req, resp);
        verify(service, times(1)).findAll();
        verify(resp, times(2)).setStatus(HttpServletResponse.SC_OK);
        verify(resp, times(2)).getWriter();
    }


    @Test
    void doPost() {
    }
}