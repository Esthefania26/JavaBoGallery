package com.bogallery.bogalleryApp.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Long Id;
    @Column (name = "NombreP", length = 40, nullable = false)
    private String NombreP;
    @Column(name = "DescripcionP", columnDefinition = "TEXT", nullable = false)
    private String DescripcionP;
    @Column (name = "TotalcuposP", length = 3, nullable = false)
    private int TotalcuposP;
    @Column (name = "PrecioP", length = 10, nullable = false)
    private int PrecioP;

    @Column(name = "Propietario_plan",length = 40, nullable = false)
    private String propietario_plan;

    @Column(name = "JornadaP", length = 30, nullable = false)
    private String JornadaP;

    @Column(name = "FechaP", columnDefinition = "DATE", nullable = false)
    private LocalDate FechaP;
    @Column(name = "Fecha_finalP", columnDefinition = "DATE", nullable = false)
    private LocalDate Fecha_finalP;

//Un plan puede generar muchas incripciones
    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL)
    private List<Inscripcion> inscripcion;


 //Muchos planes pueden pertenecer a una categoria
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "Id_categorias", nullable = true)
    private Categoria categoria;

//Un plan puede tener muchas promociones

    @JsonIgnore
    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL)
    private List<Promocion> promocion;
//Un plan puede tener muchos guias turistas

    @JsonIgnore
    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL)
    private List<GuiaTurista> guiaTurista;
//muchos planes pueden esta registrados por una empresa
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nit_empresa", nullable = true)
    private Empresa empresa;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_lugar", nullable = false)
    private Lugar lugar;
}
