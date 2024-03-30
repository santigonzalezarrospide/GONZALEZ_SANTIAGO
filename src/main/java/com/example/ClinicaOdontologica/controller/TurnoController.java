package com.example.ClinicaOdontologica.controller;

import com.example.ClinicaOdontologica.entity.Odontologo;
import com.example.ClinicaOdontologica.entity.Paciente;
import com.example.ClinicaOdontologica.entity.Turno;
import com.example.ClinicaOdontologica.service.IOdontologoService;
import com.example.ClinicaOdontologica.service.IPacienteService;
import com.example.ClinicaOdontologica.service.ITurnoService;
import com.example.ClinicaOdontologica.service.implementation.OdontologoService;
import com.example.ClinicaOdontologica.service.implementation.PacienteService;
import com.example.ClinicaOdontologica.service.implementation.TurnoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private static final Logger LOGGER = Logger.getLogger(TurnoController.class);

    private ITurnoService turnoService;
    private IOdontologoService odontologoService;
    private IPacienteService pacienteService;

    @Autowired
    public TurnoController(TurnoService turnoService, OdontologoService odontologoService, PacienteService pacienteService) {
        this.turnoService = turnoService;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Turno>> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(turnoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Turno> guardar(@RequestBody Turno turno) {
        ResponseEntity<Turno> response;
        LOGGER.info("esto trae el turno: " + turno);
        if (odontologoService.buscarPorId(turno.getOdontologo().getId()) != null &&
                pacienteService.buscarPorId(turno.getPaciente().getId()) != null) {
            response = ResponseEntity.ok(turnoService.guardar(turno));
        } else {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

    @GetMapping
    public ResponseEntity<List<Turno>> listarTodos() {
        return ResponseEntity.ok(turnoService.listarTodos());
    }

    @PutMapping
    public ResponseEntity<String> actualizar(@RequestBody Turno turno) {
        ResponseEntity<String> response;
        Optional<Turno> turnoBuscado = turnoService.buscarPorId(turno.getId());
        if (turnoBuscado != null) {
            turnoService.actualizar(turno);
            response = ResponseEntity.ok("Se actualizó el turno con id " + turno.getId());
        } else {
            response = ResponseEntity.ok().body("No se puede actualizar el turno");
        }
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) {
        Optional<Turno> turnoExistente = turnoService.buscarPorId(id);
        if (turnoExistente.isPresent()) {
            turnoService.eliminar(id);
            return ResponseEntity.ok().body("{\"message\": \"Turno con ID " + id + " eliminado correctamente.\"}");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"El turno con ID " + id + " no existe.\"}");
        }
    }


}
