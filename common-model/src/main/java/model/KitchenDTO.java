package model;

import lombok.Data;

import java.util.List;

@Data
public class KitchenDTO {
    private String name;
    private List<DishDTO> dishes;
}
