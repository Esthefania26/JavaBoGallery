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
    @Column( name = "Id_fotogradia", length = 11, nullable = false)
    private long id;

    @Column(name = "DescripcionF", columnDefinition = "TEXT", nullable = false)
    private String descripcionF;

    @Column(name = "Fotografia", nullable = false)
@Lob
    private byte[] fotografia;

}
