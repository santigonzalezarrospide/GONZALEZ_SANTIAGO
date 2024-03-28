package com.example.ClinicaOdontologica.service.implementation;

import com.example.ClinicaOdontologica.entity.Odontologo;
import com.example.ClinicaOdontologica.entity.Paciente;
import com.example.ClinicaOdontologica.exception.ResourceNotFoundException;
import com.example.ClinicaOdontologica.repository.IPacienteRepository;
import com.example.ClinicaOdontologica.service.IPacienteService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPacienteService {
    private static final Logger LOGGER = Logger.getLogger(PacienteService.class);

    private IPacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public Paciente guardar(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    @Override
    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }

    @Override
    public Optional<Paciente> buscarPorId(Long id) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);
        if (pacienteOptional.isPresent()) {
            return pacienteOptional;
        } else {
            LOGGER.info("No se encontró el paciente con  id " + id);
            throw new ResourceNotFoundException("No se encontró el paciente con  id " + id);
        }

    }


    @Override
    public void eliminar(Long id) {
        Optional<Paciente> pacienteDelete = pacienteRepository.findById(id);
        if (pacienteDelete.isEmpty()) {
            LOGGER.info("No se encontró el paciente a eliminar con id: " + id);
            throw new ResourceNotFoundException("No se encontró el paciente a eliminar con id: " + id);
        }
        pacienteRepository.deleteById(id);
    }


    @Override
    public void actualizar(Paciente paciente) {
        Optional<Paciente> pacienteUpdate = pacienteRepository.findById(paciente.getId());
        if (pacienteUpdate.isEmpty()) {
            LOGGER.info("No se pudo actualizar el paciente con id: " + paciente.getId());
            throw new ResourceNotFoundException("No se pudo actualizar el paciente con id: " + paciente.getId());
        }
        pacienteRepository.save(paciente);
    }
}
