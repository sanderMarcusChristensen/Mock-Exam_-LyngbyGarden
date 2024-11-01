package dat.dao;

import dat.dto.PlantDTO;
import dat.dto.ResellerDTO;
import dat.entities.Plant;
import dat.entities.Reseller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

public class PlantDAO implements IDAO<PlantDTO> {

    private EntityManagerFactory emf;

    public PlantDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public List<PlantDTO> getAll() {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Plant> query = em.createQuery("SELECT p FROM Plant p", Plant.class);
            List<Plant> plants = query.getResultList();
            List<PlantDTO> plantDTOs = new ArrayList<>();
            for (Plant plant : plants) {
                plantDTOs.add(new PlantDTO(plant));
            }
            return plantDTOs;
        }
    }

    @Override
    public PlantDTO getById(Long id) {
        try (EntityManager em = emf.createEntityManager()) {
            Plant plant = em.find(Plant.class, id);
            return plant != null ? new PlantDTO(plant) : null;
        }
    }

    @Override
    public List<PlantDTO> getByType(String type) {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Plant> query = em.createQuery("SELECT p FROM Plant p WHERE p.plantType = :type", Plant.class);
            query.setParameter("type", type);
            List<Plant> plants = query.getResultList();
            List<PlantDTO> plantDTOs = new ArrayList<>();
            for (Plant plant : plants) {
                plantDTOs.add(new PlantDTO(plant));
            }
            return plantDTOs;
        }
    }

    @Override
    public PlantDTO add(PlantDTO dto) {
        Plant plant = new Plant(dto);
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(plant); // Persist the new plant entity
            em.getTransaction().commit();
            return new PlantDTO(plant); // Return DTO with the newly assigned ID
        } catch (Exception e) {
            // Handle exceptions such as validation errors, etc.
            throw new RuntimeException("Error adding plant: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(Long id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Plant plant = em.find(Plant.class, id);
            if (plant != null) {
                em.remove(plant); // Remove the plant entity
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            // Handle exceptions
            throw new RuntimeException("Error deleting plant: " + e.getMessage(), e);
        }
    }

    // Extra methods not in IDAO

    public PlantDTO deletePlant(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Plant plant = em.find(Plant.class, (long) id);
            if (plant != null) {
                PlantDTO plantDTO = new PlantDTO(plant); // Create DTO before removing
                em.remove(plant);
                em.getTransaction().commit();
                return plantDTO; // Return deleted plant as DTO
            }
            em.getTransaction().commit();
            return null; // Return null if plant not found
        } catch (Exception e) {
            throw new RuntimeException("Error deleting plant: " + e.getMessage(), e);
        }
    }

    public ResellerDTO addPlantToReseller(int resellerId, int plantId) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Reseller reseller = em.find(Reseller.class, (long) resellerId);
            Plant plant = em.find(Plant.class, (long) plantId);
            if (reseller != null && plant != null) {
                reseller.getPlants().add(plant); // Add plant to reseller
                em.persist(reseller); // Update reseller in the database
                em.getTransaction().commit();
                return new ResellerDTO(reseller); // Return updated reseller DTO
            }
            em.getTransaction().commit();
            return null; // Return null if either reseller or plant not found
        } catch (Exception e) {
            throw new RuntimeException("Error adding plant to reseller: " + e.getMessage(), e);
        }
    }

    public List<PlantDTO> getPlantsByReseller(int resellerId) {
        try (EntityManager em = emf.createEntityManager()) {
            Reseller reseller = em.find(Reseller.class, (long) resellerId);
            if (reseller != null) {
                List<PlantDTO> plantDTOs = new ArrayList<>();
                for (Plant plant : reseller.getPlants()) {
                    plantDTOs.add(new PlantDTO(plant)); // Convert each plant to DTO
                }
                return plantDTOs;
            }
            return new ArrayList<>(); // Return empty list if reseller not found
        } catch (Exception e) {
            throw new RuntimeException("Error fetching plants by reseller: " + e.getMessage(), e);
        }
    }
}
