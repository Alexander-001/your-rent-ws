package com.yourrent.your_rent_ws.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "El nombre no puede estar vacio.")
    private String name;
    @NotNull(message = "El precio no puede estar vacio.")
    private Long price;
    @NotBlank(message = "La descripción no puede estar vacia.")
    private String description;
    @NotBlank(message = "La ubicación no puede estar vacia.")
    private String location;
    @NotBlank(message = "La imagen no puede estar vacia.")
    private String images;
    @NotBlank(message = "La categoria no puede estar vacia.")
    private String category;
    @Column(name = "date_publish")
    @NotBlank(message = "La fecha de publicación no puede estar vacia.")
    private String datePublish;
    @NotBlank(message = "El estado no puede estar vacio.")
    private String status;

}
