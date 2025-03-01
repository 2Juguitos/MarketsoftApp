package com.proyectoMarketsoft.crudApp.Modelo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;



@Entity
@Table(name = "tbl_categoria")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtbl_Categoria")
    private Integer idCategoria;

    @Column(name = "nombre_Categoria", nullable = false, length = 45)
    private String nombreCategoria;

    @Column(name = "fecha_Registrocategoria", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaRegistroCategoria;



}
