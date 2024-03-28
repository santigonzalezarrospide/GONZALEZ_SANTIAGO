package com.example.ClinicaOdontologica.service;

import com.example.ClinicaOdontologica.entity.Odontologo;
import com.example.ClinicaOdontologica.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IOdontologoService {
    Odontologo guardar(Odontologo odontologo);
    List<Odontologo> listarTodos();
    Optional<Odontologo> buscarPorId(Long id) throws ResourceNotFoundException;
    void actualizar(Odontologo odontologo) throws ResourceNotFoundException;
    void eliminar(Long id)throws ResourceNotFoundException;
}
