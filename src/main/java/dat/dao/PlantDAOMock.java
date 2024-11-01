package dat.dao;

import dat.dto.PlantDTO;
import dat.entities.Plant;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PlantDAOMock implements IPlantDAOMock<PlantDTO>{

    private static List<PlantDTO> plants = new ArrayList<>();
    private static Long id;

    private static EntityManagerFactory emf;
    private static PlantDAOMock instance;

    public static PlantDAOMock getInstance( EntityManagerFactory _emf) {

        if (instance == null) {
            emf = _emf;
            instance = new PlantDAOMock();
        }

        id = 1L;

        // just to have some plants in there
        PlantDTO p1 = new PlantDTO(id++,"Rose","Albertine", 400,199);
        PlantDTO p2 = new PlantDTO(id++,"Bush","Aronia", 200,169);
        plants.add(p1);
        plants.add(p2);

        return instance;
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

        if( plant.getPlantType().isEmpty()){
            throw new IllegalArgumentException("Plant type cannot be empty");
        }

        if( plant.getMaxHeight() == 0){
            throw new IllegalArgumentException("Plant max height cannot be 0");
        }

        if( plant.getPrice() == 0){
            throw new IllegalArgumentException("Plant pris cannot be 0");
        }

        if( plant.getName() == null){
            throw new IllegalArgumentException("Plant name cannot be null");
        }

        plant.setId(id++);
        plants.add(plant);
        return plant;
    }

    // do i have to use ?

    public List<PlantDTO> getPlantsWithMaxHeight(int maxHeight) {
        return plants.stream()
                .filter(plant -> plant.getMaxHeight() <= maxHeight)
                .collect(Collectors.toList());
    }

    public List<String> getPlantNames() {
        return plants.stream()
                .map(PlantDTO::getName)  // Map each plant to its name
                .collect(Collectors.toList());
    }

    public List<PlantDTO> getPlantsSortedByName() {
        return plants.stream()
                .sorted(Comparator.comparing(PlantDTO::getName))
                .collect(Collectors.toList());
    }

    //used with this version of severity

    public boolean validatePrimaryKey(Integer integer) {
        try (EntityManager em = emf.createEntityManager()) {
            Plant hotel = em.find(Plant.class, integer);
            return hotel != null;
        }
    }
}
