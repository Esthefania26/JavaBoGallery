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
    private Long Id;

    @Column( name = "NombreACT", length = 30, nullable = false)
    private String NombreACT;

    @Column(name = "DescripcionACT", columnDefinition = "TEXT", nullable = false)
    private String DescripcionACT;

    @Column(name = "Fecha_inicioACT", columnDefinition = "DATETIME",nullable = false)
    private LocalDateTime Fecha_inicioACT;

    @Column(name = "Fecha_finACT", columnDefinition = "DATETIME",nullable = false)
    private LocalDateTime Fecha_finACT;

    @Column(name = "Jornada", length = 100, nullable = false)
    private String Jornada;


    @Column( name = "Valor", length = 10, nullable = false)
    private int Valor;

    @Column( name = "URL_ACT",  nullable = false)
    private String URL_ACT;

    //muchas actividades pueden estar en un lugar
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_lugar", nullable = false)
    private Lugar lugar;
}
