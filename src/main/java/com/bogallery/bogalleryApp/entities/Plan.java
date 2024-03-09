package com.bogallery.bogalleryApp.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name ="planes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "Id_planes", length =  11, nullable = false)
    private long Id;
    @Column (name = "NombreP", length = 40, nullable = false)
    private String nombreP;
    @Column(name = "DescripcionP", columnDefinition = "TEXT", nullable = false)
    private String descripcionP;
    @Column (name = "TotalcuposP", length = 3, nullable = false)
    private int totalcuposP;
    @Column (name = "PrecioP", length = 10, nullable = false)
    private int precioP;

    @Column(name = "Propietario_plan",length = 40, nullable = false)
    private String propietario_plan;

    @Column(name = "JornadaP", length = 30, nullable = false)
    private String jornadaP;

    @Column(name = "FechaP", columnDefinition = "DATETIME", nullable = false)
    private LocalDateTime fechaP;
    @Column(name = "Fecha_finalP", columnDefinition = "DATETIME", nullable = false)
    private LocalDateTime fechafinalP;

//Un plan puede generar muchas incripciones
    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL)
    private List<Inscripcion> inscripcion;


 //Muchos planes pueden pertenecer a una categoria
    @ManyToOne
    @JoinColumn(name = "Id_categorias", nullable = false)
    private Categoria categoria;

//Un plan puede tener muchas promociones
    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL)
    private List<Promocion> promocion;
//Un plan puede tener muchos guias turistas
    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL)
    private List<GuiaTurista> guiasTuristas;
}
