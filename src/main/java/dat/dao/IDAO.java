package dat.dao;

import dat.dto.PlantDTO;
import dat.dto.ResellerDTO;
import dat.entities.Plant;
import dat.entities.Reseller;

import java.util.List;
import java.util.Set;

public interface IDAO<T> {

    List<T> getAll();
    T getById(Long id);
    List<T> getByType(String type);
    T add(PlantDTO dto);
    void delete(Long id);

    PlantDTO deletePlant(int id);
    ResellerDTO addPlantToReseller(int resellerId, int plantId);
    List<PlantDTO> getPlantsByReseller(int resellerId);



}
