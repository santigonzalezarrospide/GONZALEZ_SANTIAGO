package com.example.ClinicaOdontologica.service.implementation;

import com.example.ClinicaOdontologica.entity.Odontologo;
import com.example.ClinicaOdontologica.repository.IOdontologoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OdontologoServiceTest {
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private IOdontologoRepository odontologoRepository;


    @Test
    void guardarTest() {
        //Creo un odontologo
        Odontologo odontologo = new Odontologo();
        odontologo.setId(1L);
        odontologo.setNombre("Santiago");
        odontologo.setApellido("Gonzalez");
        odontologo.setMatricula("SG1010");
        //Guardo el odontologo
        odontologoService.guardar(odontologo);
        //Verifico que no sea null dsepues de guardar
        assertNotNull(odontologo);
    }

    @Test
    void buscarPorIdTest() {
        // ID del odontólogo existente
        Long id = 2L;
        // Crear un odontólogo simulado con el ID especificado
        Odontologo odontologo = new Odontologo();
        odontologo.setId(id);
        odontologo.setNombre("Santiago");
        odontologo.setApellido("Gonzalez");
        odontologo.setMatricula("SG1010");

        // Llamar al método del servicio que queremos probar
        Optional<Odontologo> odontologoEncontrado = odontologoService.buscarPorId(id);
        // Verificar que el Optional devuelto no esté vacío
        assertTrue(odontologoEncontrado.isPresent());
    }

    @Test
    void listarTodosTest() {
        //Creo odontologos
        Odontologo odontologo1 = new Odontologo();
        odontologo1.setId(3L);
        odontologo1.setNombre("Santiago");
        odontologo1.setApellido("Gonzalez");
        odontologo1.setMatricula("SG1010");

        Odontologo odontologo2 = new Odontologo();
        odontologo2.setId(4L);
        odontologo2.setNombre("Santiago");
        odontologo2.setApellido("Gonzalez");
        odontologo2.setMatricula("SG1010");

        // Los agrego a la lista
        List<Odontologo> odontologos = new ArrayList<>();
        odontologos.add(odontologo1);
        odontologos.add(odontologo2);

        // Llamar al método
        List<Odontologo> odontologosObtenidos = odontologoService.listarTodos();

        // Verificar que la lista devuelta no sea nula
        assertNotNull(odontologosObtenidos);
    }

    @Test
    void actualizarTest() {
        // Crear un odontólogo simulado
        Odontologo odontologo = new Odontologo();
        odontologo.setId(1L);
        odontologo.setNombre("Santiago");
        odontologo.setApellido("Gonzalez");
        odontologo.setMatricula("SG1010");

        // Guardar el odontólogo en el repositorio
        odontologoRepository.save(odontologo);

        // Actualizar el odontólogo
        odontologo.setNombre("Juan");
        odontologo.setApellido("Perez");
        odontologo.setMatricula("JP2020");
        odontologoService.actualizar(odontologo);

        // Obtener el odontólogo actualizado del repositorio
        Odontologo odontologoActualizado = odontologoRepository.findById(odontologo.getId()).orElse(null);

        // Verificar que el odontólogo actualizado coincida con los nuevos datos
        Assertions.assertThat(odontologoActualizado).isNotNull();
        Assertions.assertThat(odontologoActualizado.getNombre()).isEqualTo("Juan");
        Assertions.assertThat(odontologoActualizado.getApellido()).isEqualTo("Perez");
        Assertions.assertThat(odontologoActualizado.getMatricula()).isEqualTo("JP2020");
    }

    @Test
    void eliminarTest() {
        // ID del odontólogo existente
        Long id = 1L;
        // Crear un odontólogo para verificar su existencia
        Odontologo odontologo = new Odontologo();
        odontologo.setId(id);
        odontologo.setNombre("Santiago");
        odontologo.setApellido("Gonzalez");
        odontologo.setMatricula("SG1010");
        // Guardar el odontólogo en el repositorio
        odontologoRepository.save(odontologo);

        // Llamar al método del servicio que queremos probar
        odontologoService.eliminar(id);

        // Verificar que el odontólogo se ha eliminado correctamente
        assertFalse(odontologoRepository.existsById(id));
    }
}
