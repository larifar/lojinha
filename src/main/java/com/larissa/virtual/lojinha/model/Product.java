package com.larissa.virtual.lojinha.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "product")
public class Product implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private Boolean available = false;

    @Column(nullable = false)
    @NotBlank(message = "O nome do produto deve ser informado.")
    @Size(min = 3, message = "O número minimo de carateres é 3.")
    private String name;
    @Column(nullable = false, columnDefinition = "text")
    @NotBlank(message = "A descrição do produto deve ser informada.")
    @Size(min = 10, message = "O número minimo de carateres é 10.")
    private String description;
    @Column(nullable = false)
    @NotBlank(message = "As dimensões devem ser informadas.")
    private String dimensions;
    @Column(nullable = false)
    @NotNull(message = "Valor não pode ser nulo.")
    private BigDecimal value;
    @Column(name = "video_url")
    private String videoUrl;
    @ManyToOne
    @JoinColumn(nullable = false)
    @NotNull(message = "O produto deve ter uma categoria.")
    private ProductCategoryModel category;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public ProductCategoryModel getCategory() {
        return category;
    }

    public void setCategory(ProductCategoryModel category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return getId() == product.getId();
    }

    @Override
    public int hashCode() {
        return (int) (getId() ^ (getId() >>> 32));
    }
}
