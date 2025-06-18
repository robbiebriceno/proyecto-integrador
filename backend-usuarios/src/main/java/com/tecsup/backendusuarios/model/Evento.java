package com.tecsup.backendusuarios.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "eventos")

public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200, nullable = false)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "fecha_inicio")
    private LocalDateTime fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDateTime fechaFin;

    @Column(length = 100)
    private String ubicacion;

    @Column
    private Integer capacidad;

    private String imagen;

    @ManyToOne
    @JoinColumn(name = "carrera_id")
    private Carrera carrera;    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private User autor;

    public Evento() {}

    public Evento(Long id, User autor, Carrera carrera, String imagen, String ubicacion, LocalDateTime fechaFin, LocalDateTime fechaInicio, String descripcion, String titulo, Integer capacidad) {
        this.id = id;
        this.autor = autor;
        this.carrera = carrera;
        this.imagen = imagen;
        this.ubicacion = ubicacion;
        this.fechaFin = fechaFin;
        this.fechaInicio = fechaInicio;
        this.descripcion = descripcion;
        this.titulo = titulo;
        this.capacidad = capacidad;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public User getAutor() {
        return autor;
    }

    public void setAutor(User autor) {
        this.autor = autor;
    }
}