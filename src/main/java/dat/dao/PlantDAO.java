package dat.dao;

import dat.dto.PlantDTO;
import dat.entities.Plant;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlantDAO implements IDAO<PlantDTO> {

    private EntityManagerFactory emf;

    public PlantDAO(EntityManagerFactory emf){
        this.emf = emf;
    }

    @Override
    public List<PlantDTO> getAll() {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            // You don't need a transaction for read operations (SELECT queries)
            List<Plant> plantsList = em.createQuery("SELECT h FROM Plant h", Plant.class).getResultList();

            // Convert the list of Plant entities to a list of PlantDTOs
            List<PlantDTO> plants = plantsList.stream()
                    .map(PlantDTO::new) // map each Plant to a PlantDTO
                    .collect(Collectors.toList()); // collect results into a List
            em.getTransaction().commit();

            return plants;

        } catch (Exception e) {
            e.printStackTrace(); // log the exception
        }
        return null; // return null if an error occurs
    }


    @Override
    public PlantDTO getById(Long id) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Plant plant = em.find(Plant.class, id);
            if(plant != null){
                return new PlantDTO(plant);
            }
            em.getTransaction().commit();
            return null;
        }
    }

    @Override
    public List<PlantDTO> getByType(String type) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            // Create query to get plants by their type
            TypedQuery<Plant> q = em.createQuery("SELECT p FROM Plant p WHERE p.plantType = :type", Plant.class);
            q.setParameter("type", type);

            // Fetch list of plants with the given type
            List<Plant> plantsWithType = q.getResultList(); // Use getResultList() to directly get the list

            // Initialize a list to store PlantDTOs
            List<PlantDTO> plantDTOList = new ArrayList<>();

            // Convert Plant entities to PlantDTO and add them to the list
            for (Plant plant : plantsWithType) {
                PlantDTO dto = new PlantDTO(plant);
                plantDTOList.add(dto);
            }

            em.getTransaction().commit();

            // Return the list of PlantDTOs
            return plantDTOList;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Return null in case of an exception

    }

    @Override
    public PlantDTO add(PlantDTO dto) {

        Plant plant = dto.getPlantAsEntity();

        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(plant);
            em.getTransaction().commit();

            dto.setId(plant.getId());
            return dto;

        } catch (PersistenceException e){
            e.printStackTrace();
            System.out.println("Could not add/create a new plant form dao in database" + e.getMessage());
            return null;
        }

    }

}
