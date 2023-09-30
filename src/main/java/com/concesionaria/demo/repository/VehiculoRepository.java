package com.concesionaria.demo.repository;

import com.concesionaria.demo.entity.Vehiculo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class VehiculoRepository implements IVehiculoRepository{

    private List<Vehiculo> dataBase = new ArrayList<>();

    @Override
    public Vehiculo save(Vehiculo vehiculo) {
        dataBase.add(vehiculo);
        return vehiculo;
    }

    @Override
    public List<Vehiculo> findAll() {
        return dataBase;
    }
}
