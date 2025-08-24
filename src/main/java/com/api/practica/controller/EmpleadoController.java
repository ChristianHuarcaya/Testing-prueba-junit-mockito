package com.api.practica.controller;

import com.api.practica.entidad.Empleado;
import com.api.practica.servicio.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    // Crear un empleado
    @PostMapping
    public ResponseEntity<Empleado> saveEmpleado(@RequestBody Empleado empleado) {
        Empleado nuevoEmpleado = empleadoService.save(empleado);
        return new ResponseEntity<>(nuevoEmpleado, HttpStatus.CREATED);
    }

    // Listar todos los empleados
    @GetMapping
    public ResponseEntity<List<Empleado>> getAllEmpleados() {
        return ResponseEntity.ok(empleadoService.findAll());
    }

    // Buscar empleado por ID
    @GetMapping("/{id}")
    public ResponseEntity<Empleado> getEmpleadoById(@PathVariable Long id) {
        Empleado empleado = empleadoService.findById(id);
        return ResponseEntity.ok(empleado);
    }

    // Actualizar empleado
    @PutMapping("/{id}")
    public ResponseEntity<Empleado> updateEmpleado(@PathVariable Long id, @RequestBody Empleado empleado) {
        empleado.setId(id); // aseguramos que el id de la URL sea el mismo
        Empleado actualizado = empleadoService.update(empleado);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar empleado
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpleado(@PathVariable Long id) {
        empleadoService.delete(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}

