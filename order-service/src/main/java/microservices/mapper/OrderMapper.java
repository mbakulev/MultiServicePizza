package microservices.mapper;


import microservices.entity.OrderEntity;
import microservices.entity.OrderItemEntity;
import microservices.entity.OrderStatusEntity;
import model.OrderDTO;
import model.OrderItemDTO;

public class OrderMapper {
//    public static OrderDTO toDTO(OrderEntity entity) {
//        OrderDTO dto = new OrderDTO();
//        dto.setId(entity.getId());
//        dto.setCustomerId(entity.getCustomer().getId());
//        dto.setStatusId(entity.getStatus().getId());
//        dto.setCreatedAt(entity.getCreatedAt());
//        dto.setTotalOrderAmount(entity.getTotalOrderAmount());
//
//        dto.setItems(entity.getItems().stream().map(item -> {
//            OrderItemDTO itemDTO = new OrderItemDTO();
//
//            itemDTO.setId(item.getId());
//            itemDTO.setProductId(item.getDish().getId()); // Assuming OrderItemEntity has productId
//            itemDTO.setQuantity(item.getQuantity());
//            itemDTO.setPrice(BigDecimal.valueOf(item.getDish().getPrice()));
//            return itemDTO;
//        }).collect(Collectors.toList()));
//
//        return dto;
//    }
//
//    public static OrderEntity toEntity(OrderDTO dto, UserEntity customer, OrderStatusEntity status) {
//        OrderEntity entity = new OrderEntity();
//        entity.setId(dto.getId());
//        entity.setCustomer(customer);
//        entity.setStatus(status);
//        entity.setCreatedAt(dto.getCreatedAt());
//        entity.setTotalOrderAmount(dto.getTotalOrderAmount());
//
//        List<OrderItemEntity> items = dto.getItems().stream().map(itemDTO -> {
//            OrderItemEntity item = OrderItemMapper.toEntity(itemDTO, entity.getId());
//
//            return item;
//        }).collect(Collectors.toList());
//
//        entity.setItems(items);
//        return entity;
//    }
// Entity to DTO
public static OrderDTO toDTO(OrderEntity orderEntity) {
    if (orderEntity == null) {
        return null;
    }

    OrderDTO orderDTO = new OrderDTO();
    orderDTO.setId(orderEntity.getId());
    orderDTO.setCustomerId(orderEntity.getCustomerId());
//    // orderDTO.setStatusId(orderEntity.getStatus().getId());
    orderDTO.setCreatedAt(orderEntity.getCreatedAt());
    orderDTO.setTotalOrderAmount(orderEntity.getTotalOrderAmount());

//    List<OrderItemDTO> orderItemDTOs = orderEntity.getItems().stream()
//            .map(OrderMapper::toDTO)
//            .collect(Collectors.toList());
//    orderDTO.setItems(orderItemDTOs);

    return orderDTO;
}

    // DTO to Entity
    public static OrderEntity toEntity(OrderDTO orderDTO, OrderStatusEntity orderStatusEntity) {
        System.out.println(11111);
        if (orderDTO == null) {
            return null;
        }

        System.out.println(22);
        OrderEntity orderEntity = new OrderEntity();
//         orderEntity.setId(orderDTO.getId());
//         Assuming you have a method to retrieve the customer by ID
        System.out.println(33);
        orderEntity.setCustomerId(orderDTO.getCustomerId());
        // Assuming you have a method to retrieve status by ID
        orderEntity.setStatus(orderStatusEntity);
//        orderEntity.setCreatedAt(orderDTO.getCreatedAt());
        orderEntity.setTotalOrderAmount(orderDTO.getTotalOrderAmount());
        System.out.println(44);
//        List<OrderItemEntity> orderItemEntities = orderDTO.getItems().stream()
//                .map(orderItemDTO -> toEntity(orderDTO.getId(), orderItemDTO))
//                .collect(Collectors.toList());
//        orderEntity.setItems(orderItemEntities);

        return orderEntity;
    }

    // OrderItemDTO to OrderItemEntity
    public static OrderItemEntity toEntity(OrderEntity order, OrderItemDTO orderItemDTO) {
        System.out.println("orderItemDTO");
        System.out.println(orderItemDTO);
        OrderItemEntity orderItemEntity = new OrderItemEntity();
        orderItemEntity.setOrder(order);
        orderItemEntity.setQuantity(orderItemDTO.getQuantity());
        orderItemEntity.setDishId(orderItemDTO.getDishId());  // Assuming dish ID is the product ID
//        orderItemEntity.setPrice(orderItemDTO.getPrice());

        System.out.println("orderItemEntity");
        System.out.println(orderItemEntity);
        return orderItemEntity;
    }

    // OrderItemEntity to OrderItemDTO
    public static OrderItemDTO toDTO(OrderItemEntity orderItemEntity) {
        if (orderItemEntity == null) {
            return null;
        }

        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setId(orderItemEntity.getId());
        orderItemDTO.setDishId(orderItemEntity.getDishId());  // Assuming dish reference is valid
        orderItemDTO.setQuantity(orderItemEntity.getQuantity());
//        orderItemDTO.setPrice(orderItemEntity.getPrice());

        return orderItemDTO;
    }
}
