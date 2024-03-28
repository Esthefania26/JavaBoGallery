package com.bogallery.bogalleryApp.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "fotografias")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fotografia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "Id_fotografia", length = 11, nullable = false)
    private Long id;

    @Column(name = "DescripcionF", columnDefinition = "TEXT", nullable = false)
    private String descripcionF;

    @Column(name = "Fotografia", nullable = true)
    private byte[] fotografia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Id_lugar", nullable = false)
    private  Lugar lugar;

}
