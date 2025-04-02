package com.shopnow.productservice.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
public class ProductRequest {

    @NotBlank
    private String name;

    private String description;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private Double price;

    @NotNull
    @Min(0)
    private Integer stock;
}
