package microservices.menuservice.mapper;


import microservices.menuservice.entity.DishEntity;
import model.DishDTO;

public class DishMapper {
//    public static DishDTO toDTO(DishEntity entity) {
//        DishDTO dto = new DishDTO();
//        dto.setId(entity.getId());
//        dto.setName(entity.getName());
//        dto.setKitchenId(entity.getKitchenId());
//        dto.setPrice(entity.getPrice());
//        return dto;
//    }
//
//    public static DishEntity toEntity(DishDTO dto) {
//        DishEntity entity = new DishEntity();
//        entity.setId(dto.getId());
//        entity.setName(dto.getName());
//        entity.setKitchenId(dto.getKitchenId());
//        entity.setPrice(dto.getPrice());
//        return entity;
//    }
// DishEntity to DishDTO
public static DishDTO toDTO(DishEntity dishEntity) {
    if (dishEntity == null) {
        return null;
    }

    DishDTO dishDTO = new DishDTO();
    dishDTO.setId(dishEntity.getId());
    dishDTO.setName(dishEntity.getName());
    dishDTO.setKitchenId(dishEntity.getKitchenId());
    dishDTO.setPrice(dishEntity.getPrice());

    return dishDTO;
}

    // DishDTO to DishEntity
    public static DishEntity toEntity(DishDTO dishDTO) {
        if (dishDTO == null) {
            return null;
        }

        DishEntity dishEntity = new DishEntity();
        dishEntity.setId(dishDTO.getId());
        dishEntity.setName(dishDTO.getName());
        dishEntity.setKitchenId(dishDTO.getKitchenId());
        dishEntity.setPrice(dishDTO.getPrice());

        return dishEntity;
    }
}
