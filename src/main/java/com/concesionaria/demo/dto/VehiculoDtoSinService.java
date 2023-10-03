package com.concesionaria.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class VehiculoDtoSinService {
    private String brand, model, currency;
    private int doors, countOfOwners;
    private double numberOfKilometers, price;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate manufacturingDate;
}
