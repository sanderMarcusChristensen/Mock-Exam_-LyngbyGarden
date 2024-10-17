package dat.dao;

import dat.dto.PlantDTO;

import java.util.List;
import java.util.Set;

public interface IDAO<T> {

    List<T> getAll();
    T getById(Long id);
    List<T> getByType(String type);
    T add(PlantDTO dto);

}
