package microservices.service;

import microservices.entity.OrderEntity;
import microservices.entity.OrderItemEntity;
import microservices.entity.OrderStatusEntity;
import microservices.exception.OrderNotFoundException;
import microservices.mapper.OrderMapper;
import microservices.repository.OrderItemRepository;
import microservices.repository.OrderRepository;
import microservices.repository.OrderStatusRepository;
import model.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OrderStatusRepository orderStatusRepository;

    public List<OrderDTO> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(OrderMapper::toDTO)
                .collect(Collectors.toList());
    }

    public OrderDTO getOrderById(Long orderId) {
        Optional<OrderEntity> orderEntity = orderRepository.findById(orderId);
        if (orderEntity.isPresent()) {
            return OrderMapper.toDTO(orderEntity.get());
        } else {
            throw new OrderNotFoundException("Order with id " + orderId + " not found");
        }
    }

//    @Transactional
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Optional<OrderStatusEntity> statusOpt = orderStatusRepository.findByName("NEW");

        OrderEntity order = OrderMapper.toEntity(orderDTO, statusOpt.get());
        order.setCreatedAt(java.time.LocalDateTime.now());

        System.out.println("order");
        System.out.println(order);

        OrderEntity saved = orderRepository.save(order);

        List<OrderItemEntity> orderItemEntities = orderDTO.getItems().stream().map(item -> OrderMapper.toEntity(saved, item)).collect(Collectors.toList());
        orderItemRepository.saveAll(orderItemEntities);

        return OrderMapper.toDTO(saved);
    }
}
