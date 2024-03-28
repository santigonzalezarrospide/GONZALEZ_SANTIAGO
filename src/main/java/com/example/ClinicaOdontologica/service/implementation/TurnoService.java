package com.example.ClinicaOdontologica.service.implementation;

import com.example.ClinicaOdontologica.entity.Odontologo;
import com.example.ClinicaOdontologica.entity.Turno;
import com.example.ClinicaOdontologica.exception.ResourceNotFoundException;
import com.example.ClinicaOdontologica.repository.ITurnoRepository;
import com.example.ClinicaOdontologica.service.ITurnoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements ITurnoService {
    private static final Logger LOGGER = Logger.getLogger(TurnoService.class);

    private ITurnoRepository turnoRepository;

    @Autowired
    public TurnoService(ITurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    @Override
    public Turno guardar(Turno turno) {
        return turnoRepository.save(turno);
    }

    @Override
    public List<Turno> listarTodos() {
        return turnoRepository.findAll();
    }

    @Override
    public Optional<Turno> buscarPorId(Long id) {
        Optional<Turno> turnoOptional = turnoRepository.findById(id);
        if (turnoOptional.isPresent()) {
            return turnoOptional;
        } else {
            LOGGER.info("No se encontró el paciente con  id " + id);
            throw new ResourceNotFoundException("No se encontró el turno con  id " + id);
        }
    }

    @Override
    public void eliminar(Long id) {
        Optional<Turno> turnoDelete = turnoRepository.findById(id);
        if (turnoDelete.isEmpty()) {
            LOGGER.info("No se encontró el turno a eliminar con id: " + id);
            throw new ResourceNotFoundException("No se encontró el turno a eliminar con id: " + id);
        }
        turnoRepository.deleteById(id);
    }

    @Override
    public void actualizar(Turno turno) {
        Optional<Turno> turnoUpdate = turnoRepository.findById(turno.getId());
        if (turnoUpdate.isEmpty()) {
            LOGGER.info("No se pudo actualizar el turno con id: " + turno.getId());
            throw new ResourceNotFoundException("No se pudo actualizar el turno con id: " + turno.getId());
        }
        turnoRepository.save(turno);
    }

}
