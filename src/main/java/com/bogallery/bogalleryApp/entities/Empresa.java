package com.bogallery.bogalleryApp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "empresas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Nit_empresa", length = 15, nullable = false)
    private long nit;

    @Column(name = "Nombre_em", length = 50, nullable = false)
    private String NombreEm;

    @Column(name = "Razon_social", length = 100, nullable = false)
    private String razon;

    @Column(name = "Rut", length = 15, nullable = false)
    private int rut;

    @Column(name = "Telefono_em", length = 12, nullable = false)
    private int telefono_em;

    @Column( name = "Localidad_em", length = 40, nullable = false)
    private String localidadEm;

    @Column( name = "Barrio_em", length = 40, nullable = false)
    private String barrioEm;

    @Column( name = "Direccion_em", length = 60, nullable = false)
    private String direccionEm;

    @Column(name = "Fecha_registroEm", columnDefinition = "DATETIME", nullable = false)
    private LocalDateTime fecha_registroEm;

    @Column(name = "Estado_em", length = 1, nullable = false)
    private char estadoEm;

    @Column( name = "Correo_em", length = 80, nullable = false, unique = true)
    private String correoEm;



}
