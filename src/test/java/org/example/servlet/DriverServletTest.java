package org.example.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.DriverEntity;
import org.example.service.DriverService;
import org.example.service.impl.DriverServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DriverServletTest {
    private static DriverServlet servlet;
    private static DriverService service;
    private static ObjectMapper jsonMapper;

    @BeforeAll
    static void beforeAll() {
        service = mock(DriverServiceImpl.class);
        servlet = new DriverServlet(service);
        jsonMapper = new ObjectMapper();
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
    void saveDtoInDoPostAndDoPut() throws IOException, ServletException {
        DriverEntity driverEntity1 = new DriverEntity();
        driverEntity1.setSurname("Surname1");
        driverEntity1.setName("Name1");
        driverEntity1.setPatronymic("Patronymic1");
        String driverJson = jsonMapper.writeValueAsString(driverEntity1);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(driverJson.getBytes(StandardCharsets.UTF_8))));
        HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getReader()).thenReturn(bufferedReader);

        HttpServletResponse resp = mock(HttpServletResponse.class);
        PrintWriter printWriter = mock(PrintWriter.class);
        when(resp.getWriter()).thenReturn(printWriter);
        servlet.doPost(req, resp);
        verify(service, times(1)).save(any());
        verify(resp, times(1)).setStatus(HttpServletResponse.SC_OK);
        verify(resp, times(1)).getWriter();

        BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(driverJson.getBytes(StandardCharsets.UTF_8))));

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