package com.concesionaria.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Service{
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private String kilometers, descriptions;
}
