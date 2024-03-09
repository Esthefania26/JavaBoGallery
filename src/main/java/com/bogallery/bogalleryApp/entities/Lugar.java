package com.bogallery.bogalleryApp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.time.LocalDate;

@Entity
@Table(name = "lugares")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lugar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "Id_lugar", length = 11, nullable = false)
    private long id;

    @Column( name = "NombreL", length = 50, nullable = false)
    private String nombreL;

    @Column( name = "LocalidadL", length = 40, nullable = false)
    private String localidadL;

    @Column( name = "BarrioL", length = 40, nullable = false)
    private String barrioL;

    @Column( name = "DireccionL", length = 60, nullable = false)
    private String direccionL;

    @Column( name = "TipoL", length = 40, nullable = false)
    private String tipoL;

    @Column(name = "DescripcionL", columnDefinition = "TEXT", nullable = false)
    private String descripcionL;

    @Column( name = "Fecha_PublicacionL",  nullable = false)
    private LocalDate fechanPL;


    //muchos lugares van a ser registrados por un usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="Id_usu", nullable = false)
    private Usuario usuario;

}
