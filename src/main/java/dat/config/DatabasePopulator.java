package dat.config;

import dat.dao.PlantDAO;
import dat.dao.ResellerDAO;
import dat.dto.PlantDTO;
import dat.dto.ResellerDTO;
import jakarta.persistence.EntityManagerFactory;

public class DatabasePopulator {

    private final PlantDAO plantDAO;
    private final ResellerDAO resellerDAO;

    public DatabasePopulator(EntityManagerFactory emf) {
        this.plantDAO = new PlantDAO(emf);
        this.resellerDAO = new ResellerDAO(emf);
    }

    public void populateDatabase() {
        // Add dummy plants to the database
        PlantDTO plant1 = new PlantDTO(null, "Rose", "Red Beauty", 250.00, 400);
        PlantDTO plant2 = new PlantDTO(null, "Lily", "White Elegance", 300.00, 500);
        PlantDTO plant3 = new PlantDTO(null, "Sunflower", "Golden Giant", 150.00, 300);

        plantDAO.add(plant1);
        plantDAO.add(plant2);
        plantDAO.add(plant3);

        ResellerDTO r1 = new ResellerDTO(null,"LyngbyPlantecenter","Fiskeovvej 18","33212334");
        ResellerDTO r2 = new ResellerDTO(null,"Glostrup","<Tværvej 39 >","32233232");
        ResellerDTO r3 = new ResellerDTO(null,"Holbæk","Stenhusvej 49","594435093");

        resellerDAO.create(r1);
        resellerDAO.create(r2);
        resellerDAO.create(r3);

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
