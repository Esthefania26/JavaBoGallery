package com.bogallery.bogalleryApp.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name ="categorias")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "Id_categorias", length =  11, nullable = false)
    private Long Id;
    @Column(name = "DescripcionP", columnDefinition = "TEXT", nullable = false)
    private String descripcionC;

//una categoria puede tener muchos planes
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<Plan> plan;
}
