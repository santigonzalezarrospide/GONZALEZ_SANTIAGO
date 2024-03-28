package com.example.ClinicaOdontologica.service;

import com.example.ClinicaOdontologica.entity.Paciente;
import com.example.ClinicaOdontologica.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IPacienteService {
    Paciente guardar(Paciente paciente);

    List<Paciente> listarTodos();

    Optional<Paciente> buscarPorId(Long id) throws ResourceNotFoundException;

    void eliminar(Long id) throws ResourceNotFoundException;

    void actualizar(Paciente paciente) throws ResourceNotFoundException;
}
