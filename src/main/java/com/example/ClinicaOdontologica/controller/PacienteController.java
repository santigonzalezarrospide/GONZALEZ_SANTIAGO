package com.example.ClinicaOdontologica.controller;

import com.example.ClinicaOdontologica.entity.Odontologo;
import com.example.ClinicaOdontologica.entity.Paciente;
import com.example.ClinicaOdontologica.service.IPacienteService;
import com.example.ClinicaOdontologica.service.implementation.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private IPacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Paciente>> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Paciente> guardar(@RequestBody Paciente paciente) {
        return ResponseEntity.ok(pacienteService.guardar(paciente));
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listarTodos() {
        return ResponseEntity.ok(pacienteService.listarTodos());
    }

    @PutMapping
    public ResponseEntity<String> actualizar(@RequestBody Paciente paciente) {
        ResponseEntity<String> response;
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorId(paciente.getId());
        if (pacienteBuscado != null) {
            pacienteService.actualizar(paciente);
            response = ResponseEntity.ok("Se actualizó el paciente con id " + paciente.getId());
        } else {
            response = ResponseEntity.ok().body("No se puede actualizar el paciente");
        }
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) {
        Optional<Paciente> pacienteExistente = pacienteService.buscarPorId(id);
        if (pacienteExistente.isPresent()) {
            pacienteService.eliminar(id);
            return ResponseEntity.ok().body("{\"message\": \"Paciente con ID " + id + " eliminado correctamente.\"}");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"El paciente con ID " + id + " no existe.\"}");
        }
    }


}
