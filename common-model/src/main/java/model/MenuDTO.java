package model;

import lombok.Data;

import java.util.List;

@Data
public class MenuDTO {
    private List<KitchenDTO> kitchens;
}
