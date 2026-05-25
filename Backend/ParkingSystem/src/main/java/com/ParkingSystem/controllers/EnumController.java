package com.ParkingSystem.controllers;

import com.ParkingSystem.models.utils.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/parkingsystem/v1/enums")
public class EnumController {

    @GetMapping("/tipos-usuario")
    public List<String> getTiposUsuario() {
        return Arrays.stream(TipoUsuario.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @GetMapping("/tipos-vehiculo")
    public List<String> getTiposVehiculo() {
        return Arrays.stream(TipoVehiculo.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @GetMapping("/estados-celda")
    public List<String> getEstadosCelda() {
        return Arrays.stream(EstadoCelda.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @GetMapping("/tipos-tarifa")
    public List<String> getTiposTarifa() {
        return Arrays.stream(TipoTarifa.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @GetMapping("/estados-estadia")
    public List<String> getEstadosEstadia() {
        return Arrays.stream(EstadoEstadia.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @GetMapping("/tipos-documento")
    public List<String> getTiposDocumento() {
        return Arrays.stream(TipoDocumento.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}