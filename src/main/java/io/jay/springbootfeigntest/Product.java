package io.jay.springbootfeigntest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Product {

    @Id
    private int id;
    private String title;
    private String description;
    private int price;
    private float discountPercentage;
    private float rating;
    private int stock;
    private String brand;
    private String category;
}
