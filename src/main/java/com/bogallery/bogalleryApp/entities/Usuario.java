package com.bogallery.bogalleryApp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.time.LocalDate;

@Entity
@Table(name ="usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor


public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "Id_usu", length =  11, nullable = false)
    private Long Id;



    @Column (name = "Nombre_usu", length = 30, nullable = false)
    private String nombre;
    @Column (name = "Apellido_uso", length = 30, nullable = false)
    private String apellido;
    @Column (name = "Edad", length = 2, nullable = false)
    private int edad;
    @Column (name = "Direccion_usu", length = 80, nullable = false)
    private String direccion;
    @Column (name = "Fecha_usu", nullable = false)
    private LocalDate fecha_usu;

    @Column (name = "Telefono_usu", length = 12, nullable = false)
    private int telefono;
    @Column (name = "Correo_usu", length = 80, nullable = false)
    private String coreo;
    @Column (name = "Password_usu", length = 50, nullable = false)
    private String passwaord;
    @Column (name = "Primer_idioma", length = 50, nullable = false)
    private String primerI;
    @Column (name = "Segundo_idioma", length = 50, nullable = false)
    private String segundoI;
    @Column(name = "Genero_usu", nullable = false)
    @Enumerated(EnumType.STRING)
    private Genero genero;

    enum Genero {
        MASCULINO,
        FEMENINO,
        OTRO
    }
    //un usuario puede generar muchas inscripciones
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Inscripcion> inscripciones;

//Un usuario puede tener muchos roles

    @ManyToMany
    @JoinTable(
            name = "usuariorol",
            joinColumns = @JoinColumn(name = "Id_usu",referencedColumnName  = "Id_usu"),
            inverseJoinColumns = @JoinColumn(name = "Id_rol", referencedColumnName = "Id_rol")
    )

    private List<Rol> rol;


//un usuario puede registrar muchos lugares
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", cascade =  CascadeType.ALL)
    private List<Lugar> lugar;
}

