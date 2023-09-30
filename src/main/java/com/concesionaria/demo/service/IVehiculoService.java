package com.concesionaria.demo.service;

import com.concesionaria.demo.dto.VehiculoDto;
import com.concesionaria.demo.dto.VehiculoDtoSinService;
import com.concesionaria.demo.dto.request.ResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IVehiculoService {
    ResponseDto addVehiculo(VehiculoDto vehiculo);
    List<VehiculoDtoSinService> listVehichles ();
    List<VehiculoDto> listVehichlesDate(String since, String to);

    List<VehiculoDto> listVehichlesPrice(String since, String to);

    VehiculoDto searchForId(String id);
}
