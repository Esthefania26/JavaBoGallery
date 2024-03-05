package com.bogallery.bogalleryApp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "actividades")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Actividad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "Id_actividades", length = 11, nullable = false)
    private long id;

    @Column( name = "NombreACT", length = 30, nullable = false)
    private String nombreACT;

    @Column(name = "DescripcionACT", columnDefinition = "TEXT", nullable = false)
    private String descripcionACT;

    @Column(name = "Fecha_inicioACT", columnDefinition = "DATETIME", nullable = false)
    private LocalDateTime fechaInicioACT;

    @Column(name = "Fecha_finACT", columnDefinition = "DATETIME", nullable = false)
    private LocalDateTime fechaFinACT;


    @Column(name = "Jornada", length = 30, nullable = false)
    @Enumerated(EnumType.STRING)
    private Jornada jornada;
    public enum Jornada {
        MAÑANA,
        TARDE,
        NOCHE,
        OTRO
    }

    @Column( name = "Valor", length = 10, nullable = false)
    private int valor;

    @Column( name = "URL_ACT",  nullable = false)
    private String urlACT;

}
