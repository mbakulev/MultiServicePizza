package microservices.menuservice.service;

import model.DishDTO;
import model.KitchenDTO;
import model.MenuDTO;

import java.util.List;

public interface MenuService {
    public MenuDTO getMenu();
    public List<KitchenDTO> getKitchens();
    public KitchenDTO getKitchen(Long id);
    public DishDTO getDish(Long id);
}
