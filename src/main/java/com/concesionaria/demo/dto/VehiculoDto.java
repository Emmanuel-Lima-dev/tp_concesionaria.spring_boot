package com.concesionaria.demo.dto;

import com.concesionaria.demo.entity.Service;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehiculoDto {
    private String brand, model, currency;
    private int doors, countOfOwners;
    private double numberOfKilometers, price;
    private Date manufacturingDate;
    private List<Service> services;
    private int id;

}
