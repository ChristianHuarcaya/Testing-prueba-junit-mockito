package com.api.practica.repositorio;

import com.api.practica.entidad.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpleadoRepository extends JpaRepository <Empleado,Long> {
    Optional<Empleado> findByEmail (String email);
}
