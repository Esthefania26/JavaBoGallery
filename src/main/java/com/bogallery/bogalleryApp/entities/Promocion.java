package com.bogallery.bogalleryApp.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name ="propociones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Promocion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "Id_promociones", length =  11, nullable = false)
    private Long Id;

    @Column(name = "Descripcion_pro", columnDefinition = "TEXT", nullable = false)
    private String descripcionPro;

    @Column(name = "Fecha_inicio_pro", columnDefinition = "DATETIME", nullable = false)
    private LocalDateTime fechainiciopro;
    @Column(name = "Fecha_fin_pro", columnDefinition = "DATETIME", nullable = false)
    private LocalDateTime fechafinpro;
    @Column (name = "Tipo_pro", length = 30, nullable = false)
    private String tipoPro;
    @Column(name = "Condiciones_pro", columnDefinition = "TEXT", nullable = false)
    private String condicionesPro;
    @Column(name = "Disponibilidad", length = 1, nullable = false)
    private char disponibilidad;

//muchas promociones pueden esta en un plan
    @ManyToOne
    @JoinColumn(name = "Id_planes", nullable = false)
    private Plan plan;
}
