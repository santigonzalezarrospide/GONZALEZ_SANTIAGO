package com.example.ClinicaOdontologica.controller;

import com.example.ClinicaOdontologica.entity.Odontologo;
import com.example.ClinicaOdontologica.service.IOdontologoService;
import com.example.ClinicaOdontologica.service.implementation.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private IOdontologoService odontologoService;

    @Autowired
    public OdontologoController (OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    //busca por id
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Odontologo>> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(odontologoService.buscarPorId(id));
    }

    //guarda un odontologo
    @PostMapping
    public ResponseEntity<Odontologo> guardar(@RequestBody Odontologo odontologo) {
        return ResponseEntity.ok(odontologoService.guardar(odontologo));
    }

    //lista todos los odontologos
    @GetMapping
    public ResponseEntity<List<Odontologo>> listarTodos() {
        return ResponseEntity.ok(odontologoService.listarTodos());
    }

    @PutMapping
    public ResponseEntity<String> actualizar(@RequestBody Odontologo odontologo) {
        ResponseEntity<String> response;
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(odontologo.getId());
        if (odontologoBuscado != null) {
            odontologoService.actualizar(odontologo);
            response = ResponseEntity.ok("Se actualizó el odontologo con id " + odontologo.getId());
        } else {
            response = ResponseEntity.ok().body("No se puede actualizar el odontologo");
        }
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id) {
        Optional<Odontologo> odontologoExistente = odontologoService.buscarPorId(id);
        if (odontologoExistente.isPresent()) {
            odontologoService.eliminar(id);
            return ResponseEntity.ok().body("{\"message\": \"Odontólogo con ID " + id + " eliminado correctamente.\"}");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"El odontólogo con ID " + id + " no existe.\"}");
        }
    }

}

