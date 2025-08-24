package com.api.practica.servicio;

import com.api.practica.entidad.Empleado;
import com.api.practica.exception.ResourceNotFoundException;
import com.api.practica.repositorio.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmpleadoServiceImpl  implements EmpleadoService{

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public Empleado save(Empleado empleado) {
        // Validar si ya existe un empleado con el mismo email
        empleadoRepository.findByEmail(empleado.getEmail())
                .ifPresent(e -> {
                    throw new RuntimeException("Ya existe un empleado con el email: " + empleado.getEmail());
                });

        return empleadoRepository.save(empleado);
    }

    @Override
    public List<Empleado> findAll() {
        return empleadoRepository.findAll();
    }

    @Override
    public Empleado findById(Long id) {
        return empleadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado con id: " + id));
    }

    @Override
    public Empleado update(Empleado empleado) {
        // Verificar que el empleado exista antes de actualizar
        Empleado empleadoExistente = empleadoRepository.findById(empleado.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No se puede actualizar. Empleado no encontrado con id: " + empleado.getId()));

        empleadoExistente.setNombre(empleado.getNombre());
        empleadoExistente.setApellido(empleado.getApellido());
        empleadoExistente.setSueldo(empleado.getSueldo());
        empleadoExistente.setEmail(empleado.getEmail());

        return empleadoRepository.save(empleadoExistente);
    }

    @Override
    public void delete(Long id) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado con id: " + id));

        empleadoRepository.delete(empleado);
    }
}
