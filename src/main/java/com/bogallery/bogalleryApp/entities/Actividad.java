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
    private Long id;

    @Column( name = "NombreACT", length = 30, nullable = false)
    private String nombreACT;

    @Column(name = "DescripcionACT", columnDefinition = "TEXT", nullable = false)
    private String descripcionACT;

    @Column(name = "Fecha_inicioACT", columnDefinition = "DATETIME", nullable = false)
    private LocalDateTime fechaInicioACT;

    @Column(name = "Fecha_finACT", columnDefinition = "DATETIME", nullable = false)
    private LocalDateTime fechaFinACT;

    @Column(name = "Jornada", length = 100, nullable = false)
    private String jornada;


    @Column( name = "Valor", length = 10, nullable = false)
    private int valor;

    @Column( name = "URL_ACT",  nullable = false)
    private String urlACT;

    //muchas actividades pueden estar en un lugar
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_lugar", nullable = false)
    private Lugar lugar;
}
