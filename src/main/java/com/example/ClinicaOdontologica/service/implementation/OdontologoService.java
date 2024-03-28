package com.example.ClinicaOdontologica.service.implementation;

import com.example.ClinicaOdontologica.entity.Odontologo;
import com.example.ClinicaOdontologica.exception.ResourceNotFoundException;
import com.example.ClinicaOdontologica.repository.IOdontologoRepository;
import com.example.ClinicaOdontologica.service.IOdontologoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IOdontologoService {
    private static final Logger LOGGER = Logger.getLogger(OdontologoService.class);

    private IOdontologoRepository odontologoRepository;

    @Autowired
    public OdontologoService(IOdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        return odontologoRepository.save(odontologo);
    }

    @Override
    public List<Odontologo> listarTodos() {
        return odontologoRepository.findAll();
    }

    @Override
    public Optional<Odontologo> buscarPorId(Long id) {
        Optional<Odontologo> odontologoOptional = odontologoRepository.findById(id);
        if (odontologoOptional.isPresent()) {
            return odontologoOptional;
        } else {
            LOGGER.info("No se encontró el odontólogo con  id " + id);
            throw new ResourceNotFoundException("No se encontró el odontólogo con  id " + id);
        }
    }

    @Override
    public void actualizar(Odontologo odontologo) {
        Optional<Odontologo> odontologoUpdate = odontologoRepository.findById(odontologo.getId());
        if (odontologoUpdate.isEmpty()) {
            LOGGER.info("No se pudo actualizar el odontólogo con id: " + odontologo.getId());
            throw new ResourceNotFoundException("No se pudo actualizar el odontólogo con id: " + odontologo.getId());
        }
        odontologoRepository.save(odontologo);
    }

    @Override
    public void eliminar(Long id) {
        Optional<Odontologo> odontologoDelete = odontologoRepository.findById(id);
        if (odontologoDelete.isEmpty()) {
            LOGGER.info("No se encontró el odontólogo a eliminar con id: " + id);
            throw new ResourceNotFoundException("No se encontró el odontólogo a eliminar con id: " + id);
        }
        odontologoRepository.deleteById(id);
    }


}
