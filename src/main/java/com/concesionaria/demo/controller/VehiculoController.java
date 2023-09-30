package com.concesionaria.demo.controller;


import com.concesionaria.demo.dto.VehiculoDto;
import com.concesionaria.demo.service.IVehiculoService;
import com.concesionaria.demo.service.VehiculoServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api")
public class VehiculoController {

    private IVehiculoService service;

    public VehiculoController(VehiculoServiceImp service){
        this.service = service;
    }

    @PostMapping("/vehicles")
    public ResponseEntity<?> addVehiculo (@RequestBody VehiculoDto vehiculo){
        return new ResponseEntity<>(service.addVehiculo(vehiculo), HttpStatus.OK);
    }

    @GetMapping("/vehicles")
    public ResponseEntity<?> listVehicles(){
        return new ResponseEntity<>(service.listVehichles(), HttpStatus.OK);
    }

    @GetMapping("/vehicles/dates")
    public ResponseEntity<?> listVehiclesDate(@RequestParam String since, @RequestParam String to) {
        return new ResponseEntity<>(service.listVehichlesDate(since, to), HttpStatus.OK);
    }

    @GetMapping("/vehicles/prices")
    public ResponseEntity<?> listVehiclesPrices(@RequestParam String since, @RequestParam String to) {
        return new ResponseEntity<>(service.listVehichlesPrice(since, to), HttpStatus.OK);
    }

    @GetMapping("/vehicles/{id}")
    public ResponseEntity<?> searchForId(@PathVariable String id){
        return new ResponseEntity<>(service.searchForId(id), HttpStatus.OK);
    }

}
