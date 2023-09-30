package com.concesionaria.demo.repository;

import com.concesionaria.demo.entity.Vehiculo;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface IVehiculoRepository{
    Vehiculo save(Vehiculo persona);
    List<Vehiculo> findAll();
}
