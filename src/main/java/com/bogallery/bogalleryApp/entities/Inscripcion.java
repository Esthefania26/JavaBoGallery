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
    private long Id;
    @Column(name = "Fecha_insc", columnDefinition = "DATETIME", nullable = false)
    private LocalDateTime fechaInsc;
    @Column (name = "Cantidad_personas", length = 3, nullable = false)
    private int cantidad_personas;

//muchas inscipciones pueden ser generadas por un usuario
    @ManyToOne
    @JoinColumn(name = "Id_usu")
    private Usuario usuario;
//Muchas inscripciones pueden tener una novedad
    @ManyToOne
    @JoinColumn(name = "Id_novedades")
    private Novedad novedad;

 //muchas inscripciones pueden ir en un plan

    @ManyToOne
    @JoinColumn(name = "Id_planes")
    private Plan plan;
//una inscripcion puede generar muchas ventas
    @OneToMany(mappedBy = "inscripcion", cascade = CascadeType.ALL)
    private List<Venta> venta;


}
