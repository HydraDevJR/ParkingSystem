package com.ParkingSystem.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ParkingSystem.models.Tarifa;
import com.ParkingSystem.repositories.ITarifaRepositorie;

@Service
public class TarifaServicie {

    // Inyectando la dependencia al repositorio Tarifa
    @Autowired
    private ITarifaRepositorie repositorio;

    // Se programa una funcion por cada servicio que voy a ofrecer


    // funcion para guardar una tarifa
    public Tarifa guardar_tarifa(Tarifa datosTarifa){

        // validar que el tipo de tarifa no sea nulo
        if(datosTarifa.getTipo() == null){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Apreciado usuario, el tipo de tarifa es obligatorio"
            );
        }

        // validar que el valor sea mayor a cero
        if(datosTarifa.getValor() == null || datosTarifa.getValor().compareTo(BigDecimal.ZERO) <= 0){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Apreciado usuario, el valor de la tarifa debe ser mayor a cero"
            );
        }

        // Si paso todas las validaciones
        // intentare activar el guardado de los datos
        return repositorio.save(datosTarifa);

    }


    // funcion para listar todas las tarifas
    public List<Tarifa> listar_tarifas(){
        return repositorio.findAll();
    }


    // funcion para modificar una tarifa
    public Tarifa modificar_tarifa(Integer id, Tarifa datosNuevos){

        Optional<Tarifa> tarifa_que_busco = repositorio.findById(id);
        if(tarifa_que_busco.isEmpty()){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Tarifa no encontrada"
            );

        }else{

            Tarifa tarifa_encontrada = tarifa_que_busco.get();
            // modifiquemos datos
            tarifa_encontrada.setTipo(datosNuevos.getTipo());
            tarifa_encontrada.setValor(datosNuevos.getValor());
            tarifa_encontrada.setActivo(datosNuevos.getActivo());
            return repositorio.save(tarifa_encontrada);

        }

    }


    // funcion para eliminar una tarifa
    public boolean eliminar_tarifa(Integer id){

        Optional<Tarifa> tarifa_que_busco = repositorio.findById(id);
        if(tarifa_que_busco.isEmpty()){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Tarifa no encontrada"
            );

        }else{
            repositorio.deleteById(id);
            return true;
        }

    }


    // funcion para buscar una tarifa por id
    public Tarifa buscar_tarifa_por_id(Integer id){

        Optional<Tarifa> tarifa_que_busco = repositorio.findById(id);
        if(tarifa_que_busco.isEmpty()){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Tarifa no encontrada"
            );

        }else{
            return tarifa_que_busco.get();
        }

    }

}