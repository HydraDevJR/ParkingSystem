package com.ParkingSystem.models;
import java.time.LocalDate;

@Entity
@table(name = "seguridad")

public class Seguridad {
    
    public enum TipoSeguridad {
        Incidente,
        Alerta,
        Novedad,
        Emergencia
    }

    public enum TipoGravedad {
        Baja,
        Media,
        Alta,
        Critica
    }

    //id, parqueadero(Parqueadero), tipo(tipo seguridad: incidente, alerta, novedad, emergencia), descripcion, fecha creacion, gravedad(baja, media, alta, critica), resuelto, fecha resolucion

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private TipoSeguridad tipo;

    @column(name = "descripcion", nullable = false, unique = false, length = 250)
    private String descripcion;

    @column(name = "fecha_creacion", nullable = false, unique = false)
    private LocalDate fecha_creacion;

    private TipoGravedad gravedad;

    @column(name = "resuelto", nullable = false, unique = false)
    private boolean resuelto;

    @column(name = "fecha_resolucion", nullable = false, unique = false)
    private LocalDate fecha_resolucion;

    @ManyToOne
    @JoinColumn(name = "fk_parqueadero", referencedColumnName = "id")
    private Parqueadero parqueadero;

    public Seguridad(int id, Parqueadero parqueadero, TipoSeguridad tipo, String descripcion, LocalDate fecha_creacion, TipoGravedad gravedad, boolean resuelto, LocalDate fecha_resolucion){
        this.id = id;
        this.parqueadero = parqueadero;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.fecha_creacion = fecha_creacion;
        this.gravedad = gravedad;
        this.resuelto = resuelto;
        this.fecha_resolucion = fecha_resolucion;
    }

    public Seguridad() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Parqueadero getParqueadero() {
        return parqueadero;
    }

    public void setParqueadero(Parqueadero parqueadero) {
        this.parqueadero = parqueadero;
    }

    public TipoSeguridad getTipo() {
        return tipo;
    }

    public void setTipo(TipoSeguridad tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDate fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public TipoGravedad getGravedad() {
        return gravedad;
    }

    public void setGravedad(TipoGravedad gravedad) {
        this.gravedad = gravedad;
    }

    public boolean getResuelto() {
        return resuelto;
    }

    public void setResuelto(boolean resuelto) {
        this.resuelto = resuelto;
    }

    public LocalDate getFecha_resolucion() {
        return fecha_resolucion;
    }

    public void setFecha_resolucion(LocalDate fecha_resolucion) {
        this.fecha_resolucion = fecha_resolucion;
    }

}
