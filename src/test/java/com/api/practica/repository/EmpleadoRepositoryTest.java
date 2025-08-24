package com.api.practica.repository;

import com.api.practica.entidad.Empleado;
import com.api.practica.repositorio.EmpleadoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class EmpleadoRepositoryTest {
    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Test
    @DisplayName("Guardar y buscar empleado")
    void testGuardarYBuscarEmpleado() {
        // given
        Empleado empleado = new Empleado();
        empleado.setNombre("Juan");
        empleado.setApellido("Perez");
        empleado.setEmail("juan.perez@test.com");
        empleado.setSueldo(2500.0);

        // when
        Empleado empleadoGuardado = empleadoRepository.save(empleado);

        // then
        Optional<Empleado> empleadoEncontrado = empleadoRepository.findById(empleadoGuardado.getId());
        assertThat(empleadoEncontrado).isPresent();
        assertThat(empleadoEncontrado.get().getEmail()).isEqualTo("juan.perez@test.com");
    }

    @Test
    @DisplayName("Eliminar empleado")
    void testEliminarEmpleado() {
        // given
        Empleado empleado = new Empleado();
        empleado.setNombre("Maria");
        empleado.setApellido("Lopez");
        empleado.setEmail("maria.lopez@test.com");
        empleado.setSueldo(3000.0);
        empleado = empleadoRepository.save(empleado);

        // when
        empleadoRepository.delete(empleado);

        // then
        Optional<Empleado> eliminado = empleadoRepository.findById(empleado.getId());
        assertThat(eliminado).isEmpty();
    }


}
