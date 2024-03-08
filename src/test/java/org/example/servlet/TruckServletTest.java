package org.example.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.ParkingEntity;
import org.example.model.TruckEntity;
import org.example.service.ParkingService;
import org.example.service.TruckService;
import org.example.service.impl.ParkingServiceImpl;
import org.example.service.impl.TruckServiceImpl;
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

class TruckServletTest {
    private static TruckServlet servlet;
    private static TruckService service;
    private static ObjectMapper jsonMapper;

    @BeforeAll
    static void beforeAll() {
        service = mock(TruckServiceImpl.class);
        servlet = new TruckServlet(service);
        jsonMapper = new ObjectMapper();
    }

    @Test
    void doGet() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn("1").thenReturn(null);
        PrintWriter printWriter = mock(PrintWriter.class);
        when(resp.getWriter()).thenReturn(printWriter);

        TruckEntity truckEntity1 = new TruckEntity();
        truckEntity1.setModel("Volvo");
        truckEntity1.setNumber("A002AA09");

        when(service.findById(1)).thenReturn(truckEntity1);
        servlet.doGet(req, resp);
        verify(service, times(1)).findById(anyInt());
        verify(resp, times(1)).setStatus(HttpServletResponse.SC_OK);
        verify(resp, times(1)).getWriter();

        TruckEntity truckEntity2 = new TruckEntity();
        truckEntity2.setModel("Volvo2");
        truckEntity2.setNumber("A003AA09");

        List<TruckEntity> truckEntities = List.of(truckEntity1, truckEntity2);
        when(service.findAll()).thenReturn(truckEntities);
        servlet.doGet(req, resp);
        verify(service, times(1)).findAll();
        verify(resp, times(2)).setStatus(HttpServletResponse.SC_OK);
        verify(resp, times(2)).getWriter();
    }


    @Test
    void saveDtoInDoPostAndDoPut() throws IOException, ServletException {
        TruckEntity truckEntity1 = new TruckEntity();
        truckEntity1.setModel("Volvo3");
        truckEntity1.setNumber("A004AA09");
        String truckJson = jsonMapper.writeValueAsString(truckEntity1);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(truckJson.getBytes(StandardCharsets.UTF_8))));
        HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getReader()).thenReturn(bufferedReader);

        HttpServletResponse resp = mock(HttpServletResponse.class);
        PrintWriter printWriter = mock(PrintWriter.class);
        when(resp.getWriter()).thenReturn(printWriter);
        servlet.doPost(req, resp);
        verify(service, times(1)).save(any());
        verify(resp, times(1)).setStatus(HttpServletResponse.SC_OK);
        verify(resp, times(1)).getWriter();

        BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(truckJson.getBytes(StandardCharsets.UTF_8))));

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