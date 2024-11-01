package dat.dao;

import dat.dto.PlantDTO;

import java.util.List;

public interface IPlantDAOMock<T> {

    List<T> getAll();
    T getById(int id);
    List<T> getByType(String type);
    T add(T t);


}
