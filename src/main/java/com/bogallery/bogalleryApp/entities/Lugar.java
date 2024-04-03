package com.bogallery.bogalleryApp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "lugares")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lugar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "Id_lugar", length = 11, nullable = false)
    private Long id;

    @Column( name = "NombreL", length = 50, nullable = false)
    private String NombreL;

    @Column( name = "LocalidadL", length = 40, nullable = false)
    private String LocalidadL;

    @Column( name = "BarrioL", length = 40, nullable = false)
    private String BarrioL;

    @Column( name = "DireccionL", length = 60, nullable = false)
    private String DireccionL;

    @Column( name = "TipoL", length = 40, nullable = false)
    private String TipoL;

    @Column(name = "DescripcionL", columnDefinition = "TEXT", nullable = false)
    private String DescripcionL;

    @Column( name = "Fecha_PublicacionL",  nullable = false)
    private LocalDate Fecha_PublicacionL;


    //muchos lugares van a ser registrados por un usuario
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="Id_usu", nullable = false)
    private Usuario usuario;

//un lugar puede tener muchas fotografias
    @OneToMany(mappedBy = "lugar", cascade = CascadeType.ALL)
    private List<Fotografia> fotografia;

//un lugar puede tener muchso autiorelatos
    @OneToMany(mappedBy = "lugar", cascade = CascadeType.ALL)
    private List<Audio_Relato> audioRelato;

//Un lugar puede tene muchas actividades
    @OneToMany(mappedBy = "lugar", cascade = CascadeType.ALL)
    private List<Actividad> actividades;

    @OneToMany(mappedBy = "lugar", cascade = CascadeType.ALL)
    private List<Plan> planes;
}
