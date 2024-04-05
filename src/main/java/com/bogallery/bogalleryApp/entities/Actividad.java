package com.bogallery.bogalleryApp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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

    @Column(name = "DescripcionACT",  nullable = false)
    private String DescripcionACT;

    @Column(name = "Fecha_inicioACT", nullable = false)
    private LocalDate Fecha_inicioACT;

    @Column(name = "Fecha_finACT", nullable = false)
    private LocalDate Fecha_finACT;

    @Column(name = "Jornada", length = 100, nullable = false)
    private String Jornada;


    @Column( name = "Valor", length = 10, nullable = false)
    private int Valor;

    @Column( name = "URL_ACT",  nullable = false)
    private String URL_ACT;

    //muchas actividades pueden estar en un lugar
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_lugar", nullable = false)
    private Lugar lugar;
}
