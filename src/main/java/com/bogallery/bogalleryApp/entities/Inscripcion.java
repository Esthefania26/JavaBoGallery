package com.bogallery.bogalleryApp.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name ="inscripciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "Id_inscripcion", length =  11, nullable = false)
    private Long Id;
    @Column(name = "fecha_insc", columnDefinition = "DATETIME", nullable = false)
    private LocalDate fecha_insc;

    @Column (name = "Cantidad_personas", length = 3, nullable = false)
    private int cantidad_personas;

//muchas inscipciones pueden ser generadas por un usuario
    @ManyToOne
    @JoinColumn(name = "Id_usu", nullable = false)
    private Usuario usuario;
//Muchas inscripciones pueden tener una novedad
    @ManyToOne
    @JoinColumn(name = "Id_novedades", nullable = false)
    private Novedad novedad;

 //muchas inscripciones pueden ir en un plan

    @ManyToOne
    @JoinColumn(name = "Id_planes", nullable = false)
    private Plan plan;
//una inscripcion puede generar muchas ventas
    @OneToMany(mappedBy = "inscripciones", cascade = CascadeType.ALL)
    private List<Venta> venta;


}
