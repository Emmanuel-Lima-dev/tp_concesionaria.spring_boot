package com.concesionaria.demo.dto;

import com.concesionaria.demo.entity.Service;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehiculoDto {
    private String brand, model, currency;
    private int doors, countOfOwners;
    private double numberOfKilometers, price;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate manufacturingDate;

    private List<Service> services;
    private int id;

}
