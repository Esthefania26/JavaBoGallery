package com.bogallery.bogalleryApp.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name ="guiasTuristas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuiaTurista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "Id_Guia ", length =  11, nullable = false)
    private Long Id;

    @Column(name = "Descripcion_Gt", columnDefinition = "TEXT", nullable = false)
    private String descripcionGt;

//Muchos guias turistsa pueden estar en un plan
    @ManyToOne
    @JoinColumn(name = "id_plan", nullable = false)
    private Plan plan;

    @ManyToOne
    @JoinColumn(name = "id_perfilGuia", nullable = false)
    private PerfilGuia perfilGuia;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empresa", nullable = false)
    private Empresa empresa;

}
