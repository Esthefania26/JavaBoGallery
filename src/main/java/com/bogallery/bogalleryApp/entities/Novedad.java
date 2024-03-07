package com.bogallery.bogalleryApp.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Table(name ="novedades")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Novedad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_novedades", length = 11, nullable = false)
    private long Id;

    @Column(name = "DescripcionN", columnDefinition = "TEXT", nullable = false)
    private String descripcionN;
    @Column(name = "EstadoN", length = 1, nullable = false)
    private String estadoN;

//Una novedad puede pertenecer a una inscripcion
    @OneToMany(mappedBy = "novedad", cascade = CascadeType.ALL)
    private List<Inscripcion> inscripcion;
}
