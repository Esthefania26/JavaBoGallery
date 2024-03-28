package com.bogallery.bogalleryApp.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name ="pagos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "Id_pago", length =  11, nullable = false)
    private Long id;
    @Column (name = "TipoP", length = 30, nullable = false)
    private String tipoP;

    @Column(name = "DescripcioP", columnDefinition = "TEXT", nullable = false)
    private String descripcioP;

    @Column (name = "MontoTotalP", length = 11, nullable = false)
    private int montoTotalP;

    @Column (name = "CodidoComprobante", length = 11, nullable = false)
    private int codidoComprobante;

    @Column(name = "ArchivoComprobante", nullable = true)
    @Lob
    private byte[] archivoComprobante;


//muchos pagos pueden ser generados por una venta
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Id_venta", nullable = false)
    private Venta venta;

}
