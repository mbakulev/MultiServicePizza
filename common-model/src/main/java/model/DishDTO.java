package model;

import lombok.Data;

@Data
public class DishDTO {
    private Long id;
    private String name;
    private Long kitchenId;
    private double price;
}
