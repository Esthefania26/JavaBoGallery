package com.bogallery.bogalleryApp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuarioroles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuariorol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "Id_usurol", length = 11, nullable = false)
    private  long id;

    @Column(name = "Fecha_asignacionUr", columnDefinition = "DATETIME", nullable = false)
    private LocalDateTime fechaAsignacion;

    @Column(name = "Estado", length = 1, nullable = false)
    private char estado;

//Muchos usuariorol van a pertenecer a un usuario
    @ManyToOne
    @JoinColumn(name = "Id_usu")
    private Usuario usuario;

    //muchos usuariols rol van a pertencer a un rol
    @ManyToOne
    @JoinColumn(name = "Id_rol")
    private Usuariorol roles;




}
