package com.bogallery.bogalleryApp.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private byte[] fotografia;

}
