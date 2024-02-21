package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DriverServletTest {

    @Test
    void doGet() throws ServletException, IOException {
        DriverServlet driverServlet = new DriverServlet();
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        when(mockRequest.getParameter("id")).thenReturn(null);

        HttpServletResponse mockResponse = mock(HttpServletResponse.class);
        PrintWriter mockPrintWriter = mock(PrintWriter.class);
        when(mockResponse.getWriter()).thenReturn(mockPrintWriter);
        driverServlet.doGet(mockRequest, mockResponse);
    }


    @Test
    void doPost() {
    }
}