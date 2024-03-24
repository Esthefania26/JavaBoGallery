package com.bogallery.bogalleryApp.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Table(name ="perfilesGuias")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerfilGuia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "Id_perfilGuia", length =  11, nullable = false)
    private Long Id;

    @Column (name = "NombreG", length = 40, nullable = false)
    private String nombreG;
    @Column (name = "ApellidoG", length = 40, nullable = false)
    private String apellidoG;

    @Column (name = "TelefonoG", length = 3, nullable = false)
    private int telefonoG;

    @Column (name = "CorreoG", length = 40, nullable = false)
    private String correoG;

    @Column(name = "Certificado", nullable = true)
    @Lob
    private byte[] certificado;

    @Column(name = "Formacion", columnDefinition = "TEXT", nullable = false)
    private String formacion;

    @Column(name = "Lengua_sena", nullable = false)
    private Boolean lenguaSena;

    @Column (name = "Idioma_materno", length = 40, nullable = false)
    private String idiomaM;

    @Column (name = "Segundo_idioma", length = 40, nullable = false)
    private String segundoI;


    @Column(name = "GeneroG", length = 100, nullable = false)
    private String genero;


    @OneToMany(mappedBy = "perfilGuia", cascade = CascadeType.ALL)
    private List<GuiaTurista> guiaTurista;
}
