package com.variedades.marycruz.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.variedades.marycruz.record.CreaCategoria;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.awt.font.TextHitInfo;
import java.util.List;

@Entity
@Table(name = "categorias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    @JsonBackReference
@JsonIgnore
    private List<Producto> products;
    @NotBlank
    @Column(nullable = false)
    private String imageName;

    @Column(nullable = false)
    private String urlImage;
    public Categoria(CreaCategoria creaCategoria) {
        this.name = creaCategoria.name();
        this.imageName = creaCategoria.imageName();
        this.urlImage = creaCategoria.urlImage();
    }
}