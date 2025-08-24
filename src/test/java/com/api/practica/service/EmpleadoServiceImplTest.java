package com.api.practica.service;


import com.api.practica.entidad.Empleado;

import com.api.practica.repositorio.EmpleadoRepository;
import com.api.practica.servicio.EmpleadoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class EmpleadoServiceImplTest {

    @Mock
    private EmpleadoRepository empleadoRepository;

    @InjectMocks
    private EmpleadoServiceImpl empleadoService;

    private Empleado empleado;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        empleado = new Empleado();
        empleado.setId(1L);
        empleado.setNombre("Juan");
        empleado.setApellido("Perez");
        empleado.setEmail("juan@test.com");
        empleado.setSueldo(2500.0);
    }

    @Test
    void testSaveEmpleado() {
        when(empleadoRepository.save(empleado)).thenReturn(empleado);

        Empleado result = empleadoService.save(empleado);

        assertThat(result).isNotNull();
        assertThat(result.getNombre()).isEqualTo("Juan");
        verify(empleadoRepository, times(1)).save(empleado);
    }

    @Test
    void testFindAll() {
        when(empleadoRepository.findAll()).thenReturn(Arrays.asList(empleado));

        List<Empleado> empleados = empleadoService.findAll();

        assertThat(empleados).hasSize(1);
        verify(empleadoRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(empleadoRepository.findById(1L)).thenReturn(Optional.of(empleado));

        Empleado result = empleadoService.findById(1L);

        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo("juan@test.com");
    }

    @Test
    void testUpdateEmpleado() {
        when(empleadoRepository.save(empleado)).thenReturn(empleado);

        Empleado result = empleadoService.update(empleado);

        assertThat(result.getSueldo()).isEqualTo(2500.0);
        verify(empleadoRepository).save(empleado);
    }

    @Test
    void testDeleteEmpleado() {
        doNothing().when(empleadoRepository).deleteById(1L);

        empleadoService.delete(1L);

        verify(empleadoRepository, times(1)).deleteById(1L);
    }

}