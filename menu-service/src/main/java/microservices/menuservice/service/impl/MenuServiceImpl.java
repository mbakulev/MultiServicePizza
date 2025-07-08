package microservices.menuservice.service.impl;

import microservices.menuservice.entity.DishEntity;
import microservices.menuservice.entity.KitchenEntity;
import microservices.menuservice.exceptions.DishNotFoundException;
import microservices.menuservice.mapper.DishMapper;
import microservices.menuservice.mapper.KitchenMapper;
import microservices.menuservice.repository.DishRepository;
import microservices.menuservice.repository.KitchenRepository;
import microservices.menuservice.service.MenuService;
import model.DishDTO;
import model.KitchenDTO;
import model.MenuDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private DishRepository dishRepository;
    @Autowired
    private KitchenRepository kitchenRepository;

    @Override
    public MenuDTO getMenu() {
        MenuDTO menuDTO = new MenuDTO();
        menuDTO.setKitchens(this.getKitchens());
        return menuDTO;
    }

    @Override
    public List<KitchenDTO> getKitchens() {
        List<KitchenEntity> kitchenEntities = kitchenRepository.findAll();
        List<KitchenDTO> kitchenDTOS = kitchenEntities.stream()
                .peek(System.out::println)
                .map(kitchenEntity -> KitchenMapper.toDTO(kitchenEntity))
                .toList();

        return kitchenDTOS;
    }

    @Override
    public KitchenDTO getKitchen(Long id) {
        Optional<KitchenEntity> kitchenEntity = kitchenRepository.findById(id);
        if (kitchenEntity.isPresent()) {
            return KitchenMapper.toDTO(kitchenEntity.get());
        } else throw new DishNotFoundException("Dish not found id: " + id);
        // return null;
    }

    @Override
    public DishDTO getDish(Long id) {
        DishDTO dishDTO;
        Optional<DishEntity> dishEntity = dishRepository.findById(id);
        if (dishEntity.isPresent()) {
            dishDTO = DishMapper.toDTO(dishEntity.get());
            return dishDTO;
        } else throw new DishNotFoundException("Dish not found id: " + id);
    }
}
