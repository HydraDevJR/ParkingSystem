package com.ParkingSystem.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ParkingSystem.models.Celda;
import com.ParkingSystem.models.Estadia;
import com.ParkingSystem.models.Tarifa;
import com.ParkingSystem.models.Usuario;
import com.ParkingSystem.models.Vehiculo;
import com.ParkingSystem.repositories.IEstadiaRepositorio;

@Service
public class EstadiaService {

    @Autowired
    private IEstadiaRepository estadiaRepositorio;

    //Inyectar los respositorios de vehiculo y Celda (para búsquedas por vehiculo y celda)
    @Autowired
    private IVehiculoRepository vehiculoRepositorio;
    
    @Autowired
    private ICeldaRepositorio celdaRepositorio;

    // Guardar una Estadia
    public Estadia guardarEstadia(Estadia estadia) {
        // Validaciones
        if (estadia.getVehiculo() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "El vehículo es obligatorio"
            );
        }

        //validación de la celda
        if (estadia.getCelda() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "La celda es obligatoria"
            );
        }

        //validación de la fecha de inicio
        if (estadia.getFechaInicio() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "La fecha de inicio es obligatoria"
            );
        }

        //validación del estado
        if (estadia.getEstado() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "El estado de la estadia es obligatorio"
            );
        }

        //validación de la tarifa
        if (estadia.getTarifa() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "La tarifa es obligatoria para registrar la estadia"
            );
        }

        // Validar que la fecha de fin no sea anterior a la fecha de inicio
        if (estadia.getFechaFin() != null && estadia.getFechaFin().isBefore(estadia.getFechaInicio())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "La fecha de fin no puede ser anterior a la fecha de inicio"
            );
        }

        return estadiaRepositorio.save(estadia);
    }

    // Listar todas las estadias
    public List<Estadia> listarEstadias() {
        return estadiaRepositorio.findAll();
    }

    // Obtener estadia por ID
    public Estadia obtenerEstadiaById(Integer id) {

        Optional<Estadia> estadiaBuscada = estadiaRepositorio.findById(id);
        if (estadiaBuscada.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La estadia no existe"
            );
        } else {
            return estadiaBuscada.get();
        }
    }

    // Eliminar estadia por ID
    public boolean eliminarEstadia(Integer id) {

        Optional<Estadia> estadiaBuscada = estadiaRepositorio.findById(id);
        if (estadiaBuscada.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La estadia no existe"
            );
        }else {
            estadiaRepositorio.deleteById(id);
            return true;
        }
    }

    // Buscar estadias por vehículo
    public List<Estadia> obtenerEstadiasPorVehiculo(Integer id) {
        Vehiculo vehiculo = vehiculoRepositorio.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "El vehículo no existe"
            ));
        return estadiaRepositorio.findByVehiculo(vehiculo);
    }

    // Buscar estadias por celda
    public List<Estadia> obtenerEstadiasPorCelda(Integer id) {
        Celda celda = celdaRepositorio.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "La celda no existe"
            ));
        return estadiaRepositorio.findByCelda(celda);
    }

    // Buscar estadias por estado
    public List<Estadia> obtenerEstadiasPorEstado(Estadia.EstadoEstadia estado) {
        if (estado == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "El estado no puede ser nulo"
            );
        }
        return estadiaRepositorio.findByEstado(estado);
    }

    // Obtener estadias activas (En_Curso)
    public List<Estadia> obtenerEstadiasActivas() {
        return estadiaRepositorio.findByEstadoOrderByFechaInicio(Estadia.EstadoEstadia.En_Curso);
    }

    // Buscar estadias por rango de fechas
    public List<Estadia> obtenerEstadiasPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        if (fechaInicio == null || fechaFin == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Las fechas son obligatorias"
            );
        }

        if (fechaFin.isBefore(fechaInicio)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "La fecha fin no puede ser anterior a la fecha inicio"
            );
        }

        return estadiaRepositorio.findByFechaInicioBetween(fechaInicio, fechaFin);
    }

    // Buscar estadias finalizadas por rango de fechas
    public List<Estadia> obtenerEstadiasFinalizadasPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        if (fechaInicio == null || fechaFin == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Las fechas son obligatorias"
            );
        }

        if (fechaFin.isBefore(fechaInicio)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "La fecha fin no puede ser anterior a la fecha inicio"
            );
        }

        return estadiaRepositorio.findByEstadoAndFechaFinBetween(
                Estadia.EstadoEstadia.Finalizada, fechaInicio, fechaFin
        );
    }

    // Obtener estadias activas de un vehículo
    public List<Estadia> obtenerEstadiasActivasPorVehiculo(Integer id) {
        Vehiculo vehiculo = vehiculoRepositorio.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "El vehículo no existe"
            ));
        if (vehiculo == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "El vehículo no puede ser nulo"
            );
        }
        return estadiaRepositorio.findByVehiculoAndEstado(vehiculo, Estadia.EstadoEstadia.En_Curso);
    }

    // Obtener estadias por vehículo y estado
    public List<Estadia> obtenerEstadiasPorVehiculoYEstado(Vehiculo vehiculo, Estadia.EstadoEstadia estado) {
        if (vehiculo == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "El vehículo no puede ser nulo"
            );
        }

        if (estado == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "El estado no puede ser nulo"
            );
        }

        return estadiaRepositorio.findByVehiculoAndEstado(vehiculo, estado);
    }

    // Obtener estadias por celda y estado
    public List<Estadia> obtenerEstadiasPorCeldaYEstado(Celda celda, Estadia.EstadoEstadia estado) {
        if (celda == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "La celda no puede ser nula"
            );
        }

        if (estado == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "El estado no puede ser nulo"
            );
        }

        return estadiaRepositorio.findByCeldaAndEstado(celda, estado);
    }

    // Actualizar estadia
    public Estadia actualizarEstadia(Integer id, Estadia estadiaActualizada) {
        Optional<Estadia> estadiaBuscada = estadiaRepositorio.findById(id);
        if (estadiaBuscada.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "La estadia no existe"
            );

        } else {
            Estadia estadiaExistente = estadiaBuscada.get();
            // Modificar de datos de la estadia
            estadiaExistente.setVehiculo(estadiaActualizada.getVehiculo());
            estadiaExistente.setCelda(estadiaActualizada.getCelda());
            estadiaExistente.setFechaInicio(estadiaActualizada.getFechaInicio());
            estadiaExistente.setFechaFin(estadiaActualizada.getFechaFin());
            estadiaExistente.setEstado(estadiaActualizada.getEstado());
            estadiaExistente.setTarifa(estadiaActualizada.getTarifa());
            return estadiaRepositorio.save(estadiaExistente);
        }
    }

    // Obtener estadias por tarifa
    public List<Estadia> obtenerEstadiasPorTarifa(Tarifa tarifa) {
        if (tarifa == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "La tarifa no puede ser nula"
            );
        }
        return estadiaRepositorio.findByTarifa(tarifa);
    }

    // Obtener estadias activas por tarifa
    public List<Estadia> obtenerEstadiasActivasPorTarifa(Tarifa tarifa) {
        if (tarifa == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "La tarifa no puede ser nula"
            );
        }
        return estadiaRepositorio.findByTarifaAndEstado(tarifa, Estadia.EstadoEstadia.En_Curso);
    }

    // Obtener estadias finalizadas por tarifa
    public List<Estadia> obtenerEstadiasFinalizadasPorTarifa(Tarifa tarifa) {
        if (tarifa == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "La tarifa no puede ser nula"
            );
        }
        return estadiaRepositorio.findByTarifaAndEstado(tarifa, Estadia.EstadoEstadia.Finalizada);
    }

    // Servicio para eliminar una estadia en bd

    public boolean eliminar_Estadia(Integer id){

        Optional<Estadia> estadiaQueBusco = estadiaRepositorio.findById(id);

        if (estadiaQueBusco.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "La estadia no existe en BD"
            );
            
        }
        else{
            estadiaRepositorio.deleteById(id);
            return true;
        }
    }
    
    //servicio para buscar una estadia por su id
    public Estadia buscar_por_id(Integer id){
        Optional<Estadia> estadiaBuscada=estadiaRepositorio.findById(id);
        if(estadiaBuscada.isEmpty()){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "La estadia no existe en BD"
            );
        }else{
            return estadiaBuscada.get();
        }
    }

    //Servicio para modificar una estadia en BD
    public Estadia modificar_estadia(Integer id, Estadia datosNuevos){
        Optional<Estadia> estadiaQueBusco = estadiaRepositorio.findById(id);
        if(estadiaQueBusco.isEmpty()){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "El usuario no existe en BD"
            );
        }else{
            Estadia estadiaQueEncontre=estadiaQueBusco.get();
            //Defino que campos voy a editar
            //cambiemos el nombre
            estadiaQueEncontre.setNombres(datosNuevos.getNombres());
            return estadiaRepositorio.save(EstadiaQueEncontre);

        }
    }
}
