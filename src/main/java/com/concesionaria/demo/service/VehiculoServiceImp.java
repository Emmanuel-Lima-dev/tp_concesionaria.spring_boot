package com.concesionaria.demo.service;

import com.concesionaria.demo.dto.VehiculoDto;
import com.concesionaria.demo.dto.VehiculoDtoSinService;
import com.concesionaria.demo.dto.request.ResponseDto;
import com.concesionaria.demo.entity.Vehiculo;
import com.concesionaria.demo.repository.IVehiculoRepository;
import com.concesionaria.demo.repository.VehiculoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehiculoServiceImp implements IVehiculoService{

    private IVehiculoRepository repository;
    private ObjectMapper mapper;

    public VehiculoServiceImp (VehiculoRepository repository){
        this.repository = repository;
        this.mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }



    @Override
    public ResponseDto addVehiculo(VehiculoDto vehiculo) {
        Vehiculo vehiculoEntity = mapper.convertValue(vehiculo, Vehiculo.class);
        Vehiculo vehiculoAgregado = repository.save(vehiculoEntity);
        if (vehiculoAgregado != null){
            return new ResponseDto("Vehiculo agregado exitosamente");
        }

        return new ResponseDto("No se pudo agregar el vehiculo");
    }

    @Override
    public List<VehiculoDtoSinService> listVehichles() {
        return repository.findAll().stream()
                .map(vehiculo -> mapper.convertValue(vehiculo, VehiculoDtoSinService.class))
                .toList();
    }

    @Override
    public List<VehiculoDto> listVehichlesDate(String since, String to) {

        //validar existencia de los argumentos o regresar colección vacía
        if (since == null || to == null || since.isEmpty() || to.isEmpty()) {
            return Collections.emptyList();
        }

        //variables a parsear
        String fechaInicioStr = since;
        String fechaFinalStr = to;
        LocalDate fechaInicio = null;
        LocalDate fechaFin = null;

        //parseo a fechas de tipo Date
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


            fechaInicio = LocalDate.parse(fechaInicioStr);
            fechaFin = LocalDate.parse(fechaFinalStr);

        }catch (NumberFormatException e){
            e.printStackTrace();
        }

        //validacion de variables de fechas y conversión de datos
        if (fechaInicio != null && fechaFin != null) {
            //convertimos a variables de tipo final para usar en filter
            //obtener lista de vehiculos de la interfaz repository
            LocalDate finalFechaInicio = fechaInicio;
            LocalDate finalFechaFin = fechaFin;
            return repository.findAll().stream()
                    //conversión a dto y retorno de lista filtrada
                    .map(vehiculo -> mapper.convertValue(vehiculo, VehiculoDto.class))
                    .filter(vehiculo -> {
                        final LocalDate manufacturingDate = vehiculo.getManufacturingDate();
                        return manufacturingDate != null &&
                                manufacturingDate.isAfter(finalFechaInicio) &&
                                manufacturingDate.isBefore(finalFechaFin);
                    })
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public List<VehiculoDto> listVehichlesPrice(String since, String to) {

        //validar existencia de los argumentos o regresar colección vacía
        if (since == null || to == null || since.isEmpty() || to.isEmpty()) {
            return Collections.emptyList();
        }

        //variables a parsear
        String precioBaseStr = since;
        String precioFinalStr = to;
        Double precioBase = null;
        Double precioFinal = null;

        //parseo a fechas de tipo Double
        try {
            precioBase = Double.parseDouble(precioBaseStr);
            precioFinal = Double.parseDouble(precioFinalStr);
        } catch (NumberFormatException e){
            e.printStackTrace();
        }

        //validacion de variables de precios y conversión de datos
        if (precioBase != null && precioFinal != null){
            //convertimos a variables de tipo final para usar en filter
            final Double precioBaseFinal = precioBase;
            final Double precioFinalFinal = precioFinal;
            //obtener lista de vehiculos de la interfaz repository
            return repository.findAll().stream()
                    .map(vehiculo -> mapper.convertValue(vehiculo, VehiculoDto.class))
                    .filter(vehiculo -> {
                        Double precioVehiculo = vehiculo.getPrice();
                        return precioVehiculo != null &&
                                precioVehiculo >= precioBaseFinal &&
                                precioVehiculo <= precioFinalFinal;
                    })
                    .collect(Collectors.toList());
        }

        return Collections.emptyList();
    }

    @Override
    public VehiculoDto searchForId(String id) {
        try{
            Integer idNum = Integer.parseInt(id);
            if ( idNum > 0 ){
               return repository.findAll().stream()
                        .map(vehiculo -> mapper.convertValue(vehiculo, VehiculoDto.class))
                        .filter(vehiculo -> vehiculo.getId() == idNum)
                        .findFirst()
                        .orElse(null);
            }

        }catch (NumberFormatException e){
            e.printStackTrace();
            }
            return null;
        }

    }


