package com.ParkingSystem.models;

import com.ParkingSystem.models.utils.EstadoCelda;
import com.ParkingSystem.models.utils.TipoVehiculo;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "celdas")
public class Celda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, unique = true)
    private Integer id;

    @Column(name = "Codigo", nullable = false, unique = true )
    private String codigo;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_vehiculo", nullable = false, length = 20)
    private TipoVehiculo tipoVehiculo;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_celda", nullable = false, length = 20)
    private EstadoCelda estado;

    // Relación OneToMany con Estadia
    @OneToMany(mappedBy = "celda" , cascade = CascadeType.ALL)
    private List<Estadia> estadias;

    public Celda() {}

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public TipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public EstadoCelda getEstado() {
        return estado;
    }

    public void setEstado(EstadoCelda estado) {
        this.estado = estado;
    }

    public List<Estadia> getEstadias() {
        return estadias;
    }

    public void setEstadias(List<Estadia> estadias) {
        this.estadias = estadias;
    }
}