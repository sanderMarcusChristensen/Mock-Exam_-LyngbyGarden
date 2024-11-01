package dat.dao;

import dat.dto.PlantDTO;

import java.util.List;

public interface IPlantDAOMock {

    List<PlantDTO> getAll();
    PlantDTO getById(int id);
    List<PlantDTO> getByType(String type);
    PlantDTO add(PlantDTO plant);

}
