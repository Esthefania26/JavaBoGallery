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
    private String Nombre_usu;
    @Column (name = "Apellido_uso", length = 30, nullable = false)
    private String Apellido_uso;
    @Column (name = "Edad", length = 2, nullable = false)
    private int Edad;
    @Column (name = "Direccion_usu", length = 80, nullable = false)
    private String Direccion_usu;
    @Column (name = "Fecha_usu", nullable = false)
    private LocalDate Fecha_usu;

    @Column (name = "Telefono_usu", length = 12, nullable = false)
    private int Telefono_usu;
    @Column (name = "Correo_usu", length = 80, nullable = false)
    private String Correo_usu;
    @Column (name = "Password_usu", length = 50, nullable = false)
    private String Password_usu;
    @Column (name = "Primer_idioma", length = 50, nullable = false)
    private String Primer_idioma;
    @Column (name = "Segundo_idioma", length = 50, nullable = false)
    private String Segundo_idioma;

    @Column(name = "Genero_usu", length = 100, nullable = false)
    private String Genero_usu;

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

