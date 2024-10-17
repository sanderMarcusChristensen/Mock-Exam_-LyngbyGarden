package dat.util;

import dat.config.HibernateConfig;
import dat.dao.PlantDAO;
import dat.dto.PlantDTO;
import jakarta.persistence.EntityManagerFactory;

public class DatabasePopulator {

    private final PlantDAO plantDAO;

    public DatabasePopulator(EntityManagerFactory emf) {
        this.plantDAO = new PlantDAO(emf);
    }

    public void populateDatabase() {
        // Add dummy plants to the database
        PlantDTO plant1 = new PlantDTO(null, "Rose", "Red Beauty", 250.00, 400);
        PlantDTO plant2 = new PlantDTO(null, "Lily", "White Elegance", 300.00, 500);
        PlantDTO plant3 = new PlantDTO(null, "Sunflower", "Golden Giant", 150.00, 300);

        plantDAO.add(plant1);
        plantDAO.add(plant2);
        plantDAO.add(plant3);

        System.out.println("Dummy plants added to the database.");
    }

    // Add a static main method to allow running this class standalone
    public static void main(String[] args) {
        // Initialize the EntityManagerFactory using your Hibernate configuration
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("lyngbygarden");

        // Create the DatabasePopulator instance
        DatabasePopulator populator = new DatabasePopulator(emf);

        // Call the populateDatabase method to add dummy data
        populator.populateDatabase();

        // Close the EntityManagerFactory after populating the database
        emf.close();

        System.out.println("Database population complete.");
    }
}
