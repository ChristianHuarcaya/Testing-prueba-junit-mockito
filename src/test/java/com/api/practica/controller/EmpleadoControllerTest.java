package com.api.practica.controller;
import com.api.practica.entidad.Empleado;
import com.api.practica.servicio.EmpleadoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmpleadoController.class)

public class EmpleadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmpleadoService empleadoService;

    @Autowired
    private ObjectMapper objectMapper;

    public EmpleadoControllerTest() {
    }

    @Test
    void testGuardarEmpleado() throws Exception {
        Empleado empleado = new Empleado(1L, "Juan", "Perez", 2500.0, "juan@test.com");

        when(empleadoService.save(empleado)).thenReturn(empleado);

        mockMvc.perform(post("/api/empleados")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(empleado)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Juan"));
    }

    @Test
    void testListarEmpleados() throws Exception {
        Empleado empleado = new Empleado(1L, "Juan", "Perez", 2500.0, "juan@test.com");
        when(empleadoService.findAll()).thenReturn(Arrays.asList(empleado));

        mockMvc.perform(get("/api/empleados"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email").value("juan@test.com"));
    }

    @Test
    void testEliminarEmpleado() throws Exception {
        doNothing().when(empleadoService).delete(1L);

        mockMvc.perform(delete("/api/empleados/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Empleado eliminado exitosamente"));
    }
}
