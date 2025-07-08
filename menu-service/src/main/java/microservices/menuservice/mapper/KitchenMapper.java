package microservices.menuservice.mapper;


import microservices.menuservice.entity.DishEntity;
import microservices.menuservice.entity.KitchenEntity;
import model.DishDTO;
import model.KitchenDTO;

import java.util.List;
import java.util.stream.Collectors;

public class KitchenMapper {
    public static KitchenDTO toDTO(KitchenEntity entity) {
        if (entity == null) {
            return null;
        }

        KitchenDTO dto = new KitchenDTO();
        dto.setName(entity.getName());

        if (entity.getDishes() != null) {
            List<DishDTO> dishDTOs = entity.getDishes().stream()
                    .map(dishEntity -> DishMapper.toDTO(dishEntity))
                    .collect(Collectors.toList());
            dto.setDishes(dishDTOs);
        }

        System.out.println(dto);
        return dto;
    }

    public static KitchenEntity toEntity(KitchenDTO dto) {
        if (dto == null) {
            return null;
        }

        KitchenEntity entity = new KitchenEntity();
        entity.setName(dto.getName());

        if (dto.getDishes() != null) {
            List<DishEntity> dishEntities = dto.getDishes().stream()
                    .map(dishDTO -> DishMapper.toEntity(dishDTO))
                    .collect(Collectors.toList());
            entity.setDishes(dishEntities);
        }

        return entity;
    }
}
