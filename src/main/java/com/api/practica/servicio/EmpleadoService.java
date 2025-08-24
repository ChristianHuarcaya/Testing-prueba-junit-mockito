package com.api.practica.servicio;

import com.api.practica.entidad.Empleado;

import java.util.List;
import java.util.Optional;


public interface EmpleadoService {

    Empleado save(Empleado empleado);

    List<Empleado> findAll();

    Empleado findById(Long id);

    Empleado update(Empleado empleado);

    void delete(Long id);
}
