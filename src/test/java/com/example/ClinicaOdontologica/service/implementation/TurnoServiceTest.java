package com.example.ClinicaOdontologica.service.implementation;

import com.example.ClinicaOdontologica.entity.Domicilio;
import com.example.ClinicaOdontologica.entity.Odontologo;
import com.example.ClinicaOdontologica.entity.Paciente;
import com.example.ClinicaOdontologica.entity.Turno;
import com.example.ClinicaOdontologica.repository.ITurnoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TurnoServiceTest {
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private ITurnoRepository turnoRepository;


    @Test
    void guardarTest() {
        //Creo un odontologo
        Odontologo odontologo = new Odontologo();
        odontologo.setId(1L);
        odontologo.setNombre("Santiago");
        odontologoService.guardar(odontologo);
        //Creo un paciente
        Paciente paciente = new Paciente();
        paciente.setId(1L);
        paciente.setNombre("Roberto");
        pacienteService.guardar(paciente);
        // Crear un turno con el paciente y el odontólogo simulados y la fecha
        Turno turno = new Turno();
        turno.setId(1L);
        turno.setOdontologo(odontologo);
        turno.setPaciente(paciente);
        turno.setFecha(LocalDate.now());
        //Gurado el turno
        turnoService.guardar(turno);
        //Verifico que no sea null dsepues de guardar
        assertNotNull(turno);
    }

    @Test
    void buscarPorIdTest() {
        //Creo un turno
        Turno turno = new Turno();
        turno.setId(1L);
        // Llamar al método del servicio que queremos probar
        Optional<Turno> turnoEncontrado = turnoService.buscarPorId(turno.getId());
        // Verificar que el Optional devuelto no esté vacío
        assertTrue(turnoEncontrado.isPresent());
    }

    @Test
    void listarTodosTest() {
        //Creo turnos
        Turno turno1 = new Turno();
        turno1.setId(1L);
        Turno turno2 = new Turno();
        turno2.setId(2L);
        // Los agrego a la lista
        List<Turno> turnos = new ArrayList<>();
        turnos.add(turno1);
        turnos.add(turno2);
        // Llamar al método
        List<Turno> turnosObtenidos = turnoService.listarTodos();
        // Verificar que la lista devuelta no sea nula
        assertNotNull(turnosObtenidos);

    }

    @Test
    void actualizarTest() {
        // Creo  turno
        Turno turno = new Turno();
        turno.setId(1L);
        String fechaString = "2022/12/12";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate fecha = LocalDate.parse(fechaString, formatter);
        turno.setFecha(fecha);
        // Guardar el turno en el repositorio
        turnoRepository.save(turno);
        // Actualizar la fecha del turno
        String fechaActualizada = "2024/03/30";
        DateTimeFormatter formatterUpdate = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate fecha2 = LocalDate.parse(fechaActualizada, formatterUpdate);
        turno.setFecha(fecha2);
        turnoService.actualizar(turno);
        // Obtener el turno con fecha actualizada del repositorio
        Turno turnoActualizado = turnoRepository.findById(turno.getId()).orElse(null);

        // Verificar que el turno actualizado coincida con los nuevos datos

        String fechaFinal = "2024/03/30";
        DateTimeFormatter formatterFinal = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate fecha3 = LocalDate.parse(fechaFinal, formatterFinal);
        Assertions.assertThat(turnoActualizado).isNotNull();
        Assertions.assertThat(turnoActualizado.getFecha()).isEqualTo(fecha3);

    }

    @Test
    void eliminarTest() {
        // ID del turno existente
        Long id = 2L;
        // Crear un tuno para verificar su existencia
        Turno turno = new Turno();
        turno.setId(id);
        turno.setFecha(LocalDate.now());
        // Guardar el turno en el repositorio
        turnoRepository.save(turno);
        // Llamar al método del servicio que queremos probar
        turnoService.eliminar(id);
        // Verificar que el turno se ha eliminado correctamente
        assertFalse(turnoRepository.existsById(id));

    }

}
