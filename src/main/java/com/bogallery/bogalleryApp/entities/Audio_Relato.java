package com.bogallery.bogalleryApp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "audio_relatos")
@AllArgsConstructor
@NoArgsConstructor
public class Audio_Relato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "Id_AudioR", length = 11, nullable = false)
    private Long id;

    @Column( name = "NombreAR", length = 50, nullable = false)
    private String nombreAR;

    @Column(name = "DescripcionAR", columnDefinition = "TEXT", nullable = false)
    private String descripcionAR;

    @Column(name = "Fecha_publicacionAr", columnDefinition = "DATETIME", nullable = false)
    private LocalDateTime fechaPublicacionAR;

    @Column( name = "URL_AR",  nullable = false)
    private String urlAR;

    @Column( name = "Calificacion", length = 10, nullable = false)
    private int calificacion;

    //muchos autiorelatos pueden estar en un lugar
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Id_lugar", nullable = false)
    private Lugar lugar;

}
