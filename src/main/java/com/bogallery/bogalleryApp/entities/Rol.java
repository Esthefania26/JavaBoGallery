package com.bogallery.bogalleryApp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "Id_rol", length = 11, nullable = false)
    private Long id;

    @Column( name = "Nombre_rol", length = 30, nullable = false)
    private String nombreR;

    @Column( name = "Descripcion_rol", length = 150, nullable = false)
    private String descripcion_rol;

    @Column(name = "Fecha_registroR", columnDefinition = "DATETIME", nullable = false)
    private LocalDateTime fecha_registroR;

    @Column(name = "Estado", length = 1, nullable = false)
    private char estado;

    //Muchos roles pueden estar asociados a un usuario
 @ManyToMany(mappedBy = "rol")
    private List<Usuario> usuario;

//Un rol puede estar asociado a muchas empresas
    @OneToMany(mappedBy = "rol", cascade = CascadeType.ALL)
    private List<Empresa> empresa;

}
