package com.example.ClinicaOdontologica.service.implementation;

import com.example.ClinicaOdontologica.entity.Domicilio;
import com.example.ClinicaOdontologica.entity.Paciente;
import com.example.ClinicaOdontologica.repository.IPacienteRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class PacienteServiceTest {
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private IPacienteRepository pacienteRepository;

    public class PacienteEjemplo {
        public static Paciente crearPacienteEjemplo() {
            Paciente paciente = new Paciente();
            paciente.setId(1L);
            paciente.setNombre("Roberto");
            paciente.setApellido("Canesa");
            paciente.setDni("12345678");
            paciente.setFechaIngreso(LocalDate.now());
            // Crear un domicilio simulado
            Domicilio domicilio = new Domicilio();
            domicilio.setCalle("Calle Principal");
            domicilio.setNumero(123);
            domicilio.setLocalidad("Localidad");
            domicilio.setProvincia("Provincia");
            paciente.setDomicilio(domicilio);
            return paciente;
        }
        public static Paciente crearPaciente() {
            Paciente paciente = new Paciente();
            paciente.setId(2L);
            paciente.setNombre("Daiana");
            paciente.setApellido("Figueroa");
            paciente.setDni("12345678");
            paciente.setFechaIngreso(LocalDate.now());
            // Crear un domicilio simulado
            Domicilio domicilio = new Domicilio();
            domicilio.setCalle("Calle Principal");
            domicilio.setNumero(123);
            domicilio.setLocalidad("Localidad");
            domicilio.setProvincia("Provincia");
            paciente.setDomicilio(domicilio);
            return paciente;
        }
    }

    @Test
    void guardarTest() {
        // Crear un paciente de ejemplo
        Paciente paciente = PacienteEjemplo.crearPacienteEjemplo();
        // Guardar el paciente
        pacienteService.guardar(paciente);
        // Verificar que el paciente no sea null después de guardar
        assertNotNull(paciente);
    }

    @Test
    void buscarPorIdTest() {
        // Crear un paciente de ejemplo
        Paciente paciente = PacienteEjemplo.crearPacienteEjemplo();
        // Llamar al método del servicio que queremos probar
        Optional<Paciente> pacienteEncontrado = pacienteService.buscarPorId(paciente.getId());
        // Verificar que el Optional devuelto no esté vacío
        assertTrue(pacienteEncontrado.isPresent());
    }

    @Test
    void listarTodosTest() {
        // Creo pacientes
        Paciente paciente1 = PacienteEjemplo.crearPacienteEjemplo();
        Paciente paciente2 = PacienteEjemplo.crearPaciente();
        // Los agrego a la lista
        List<Paciente> pacientes = new ArrayList<>();
        pacientes.add(paciente1);
        pacientes.add(paciente2);
        // Llamar al método
        List<Paciente> pacientesObtenidos = pacienteService.listarTodos();
        // Verificar que la lista devuelta no sea nula
        assertNotNull(pacientesObtenidos);

    }

    @Test
    void actualizarTest() {
        // Creo pacientes
        Paciente paciente = new Paciente();
        paciente.setNombre("Ramiro");
        paciente.setApellido("Perez");

        // Guardar el paciente en el repositorio
        pacienteRepository.save(paciente);

        // Actualizar el paciente
        paciente.setNombre("Miguel");
        paciente.setApellido("Ramirez");
        pacienteService.actualizar(paciente);

        // Obtener el paciente actualizado del repositorio
        Paciente pacienteActualizado = pacienteRepository.findById(paciente.getId()).orElse(null);

        // Verificar que el paciente actualizado coincida con los nuevos datos
        Assertions.assertThat(pacienteActualizado).isNotNull();
        Assertions.assertThat(pacienteActualizado.getNombre()).isEqualTo("Miguel");
        Assertions.assertThat(pacienteActualizado.getApellido()).isEqualTo("Ramirez");

    }

    @Test
    void eliminarTest() {
        // ID del paciente existente
        Long id = 2L;
        // Crear un paceinte para verificar su existencia
        Paciente paciente = new Paciente();
        paciente.setId(id);
        paciente.setNombre("Santiago");
        paciente.setApellido("Gonzalez");
        // Guardar el paciente en el repositorio
        pacienteRepository.save(paciente);
        // Llamar al método del servicio que queremos probar
        pacienteService.eliminar(id);
        // Verificar que el odontólogo se ha eliminado correctamente
        assertFalse(pacienteRepository.existsById(id));
    }
}
