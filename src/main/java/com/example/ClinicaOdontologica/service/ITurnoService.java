package com.example.ClinicaOdontologica.service;

import com.example.ClinicaOdontologica.entity.Turno;
import com.example.ClinicaOdontologica.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface ITurnoService {
    Turno guardar(Turno turno);

    List<Turno> listarTodos();

    Optional<Turno> buscarPorId(Long id) throws ResourceNotFoundException;

    void eliminar(Long id) throws ResourceNotFoundException;

    void actualizar(Turno turno) throws ResourceNotFoundException;
}
