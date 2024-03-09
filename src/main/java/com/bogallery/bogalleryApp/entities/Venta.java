package com.bogallery.bogalleryApp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.time.LocalDate;

@Entity
@Table(name ="ventas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "Id_ventas", length =  11, nullable = false)
    private long Id;

    @Column (name = "Total_ventas", length = 11, nullable = false)
    private int total_ventas;

    @Column(name = "Fecha_venta", columnDefinition = "DATETIME", nullable = false)
    private LocalDateTime fecha_venta;


    @Column (name = "comision_venta", length = 11, nullable = false)
    private int Comision_venta;

//Muchas ventas estan asociados a una inscripcion
    @ManyToOne
    @JoinColumn(name = "Id_inscripcion", nullable = false)
    private Inscripcion inscripciones;

}
