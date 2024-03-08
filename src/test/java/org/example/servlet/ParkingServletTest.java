package org.example.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.DriverEntity;
import org.example.model.ParkingEntity;
import org.example.service.DriverService;
import org.example.service.ParkingService;
import org.example.service.impl.DriverServiceImpl;
import org.example.service.impl.ParkingServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class ParkingServletTest {

    private static ParkingServlet servlet;
    private static ParkingService service;
    private static ObjectMapper jsonMapper;

    @BeforeAll
    static void beforeAll() {
        service = mock(ParkingServiceImpl.class);
        servlet = new ParkingServlet(service);
        jsonMapper = new ObjectMapper();
    }

    @Test
    void doGet() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn("1").thenReturn(null);
        PrintWriter printWriter = mock(PrintWriter.class);
        when(resp.getWriter()).thenReturn(printWriter);

        ParkingEntity parkingEntity1 = new ParkingEntity();
        parkingEntity1.setAddress("Voronezh");
        parkingEntity1.setSquare(40);

        when(service.findById(1)).thenReturn(parkingEntity1);
        servlet.doGet(req, resp);
        verify(service, times(1)).findById(anyInt());
        verify(resp, times(1)).setStatus(HttpServletResponse.SC_OK);
        verify(resp, times(1)).getWriter();

        ParkingEntity parkingEntity2 = new ParkingEntity();
        parkingEntity2.setAddress("Voronezh2");
        parkingEntity2.setSquare(50);

        List<ParkingEntity> parkingEntities = List.of(parkingEntity1, parkingEntity2);
        when(service.findAll()).thenReturn(parkingEntities);
        servlet.doGet(req, resp);
        verify(service, times(1)).findAll();
        verify(resp, times(2)).setStatus(HttpServletResponse.SC_OK);
        verify(resp, times(2)).getWriter();
    }


    @Test
    void saveDtoInDoPostAndDoPut() throws IOException, ServletException {
        ParkingEntity parkingEntity1 = new ParkingEntity();
        parkingEntity1.setAddress("Voronezh");
        parkingEntity1.setSquare(40);
        String parkingJson = jsonMapper.writeValueAsString(parkingEntity1);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(parkingJson.getBytes(StandardCharsets.UTF_8))));
        HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getReader()).thenReturn(bufferedReader);

        HttpServletResponse resp = mock(HttpServletResponse.class);
        PrintWriter printWriter = mock(PrintWriter.class);
        when(resp.getWriter()).thenReturn(printWriter);
        servlet.doPost(req, resp);
        verify(service, times(1)).save(any());
        verify(resp, times(1)).setStatus(HttpServletResponse.SC_OK);
        verify(resp, times(1)).getWriter();

        BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(parkingJson.getBytes(StandardCharsets.UTF_8))));

        when(req.getReader()).thenReturn(bufferedReader2);

        servlet.doPut(req,resp);
        verify(service, times(2)).save(any());
        verify(resp, times(2)).setStatus(HttpServletResponse.SC_OK);
        verify(resp, times(2)).getWriter();
    }

    @Test
    void doDelete() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn("1");
        when(service.delete(anyInt())).thenReturn(true).thenReturn(false);
        servlet.doDelete(req, resp);
        verify(service, times(1)).delete(anyInt());
        verify(resp, times(1)).setStatus(HttpServletResponse.SC_OK);
        servlet.doDelete(req,resp);
        verify(resp, times(1)).setStatus(HttpServletResponse.SC_NOT_FOUND);
    }
}