package dat.dao;

import dat.dto.PlantDTO;
import jakarta.persistence.EntityManagerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlantDAOMock implements IPlantDAOMock{

    private static List<PlantDTO> plants = new ArrayList<>();
    private static Long id;

    public PlantDAOMock() {
        id = 1L;

        // just to have some plants in there
        PlantDTO p1 = new PlantDTO(id++,"Rose","Albertine", 400,199);
        PlantDTO p2 = new PlantDTO(id++,"Bush","Aronia", 200,169);
        plants.add(p1);
        plants.add(p2);
    }

    @Override
    public List<PlantDTO> getAll() {
        return plants;
    }

    @Override
    public PlantDTO getById(int id) {
        return plants.stream()
                .filter(plant -> plant.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<PlantDTO> getByType(String type) {
        return plants.stream() // Stream through the list
                .filter(plant -> plant.getPlantType().equalsIgnoreCase(type)) // Filter by type
                .collect(Collectors.toList()); // Collect matching plants into a list
    }

    @Override
    public PlantDTO add(PlantDTO plant) {
        plant.setId(id+1);
        plants.add(plant);
        return plant;
    }
}
