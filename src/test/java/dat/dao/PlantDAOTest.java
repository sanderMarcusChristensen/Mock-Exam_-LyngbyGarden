package dat.dao;

import dat.config.HibernateConfig;
import dat.dto.PlantDTO;
import dat.entities.Plant;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PlantDAOTest {
    private static EntityManagerFactory emfTest;
    private static PlantDAO dao;

    PlantDTO p1, p2, p3;

    @BeforeAll
    static void beforeAll() {
        emfTest = HibernateConfig.getEntityManagerFactoryForTest();
        dao = new PlantDAO(emfTest);
    }

    @BeforeEach
    void setUp() {
        // Delete all existing plants in the database
        try (EntityManager em = emfTest.createEntityManager()) {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Plant").executeUpdate();
            em.getTransaction().commit();
        }

        // Add new plants using the DAO, which manages transactions internally
        p1 = new PlantDTO(null, "Rose", "Albertine", 250.00, 400);
        p2 = new PlantDTO(null, "Lily", "Lils", 260.00, 500);
        p3 = new PlantDTO(null, "Sunflower", "Sunny", 220.00, 350);

        dao.add(p1);  // Transaction handled inside the DAO method
        dao.add(p2);  // Transaction handled inside the DAO method
        dao.add(p3);  // Transaction handled inside the DAO method
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAll() {

        int ex = 3;
        int ac = dao.getAll().size();
        assertEquals(ex, ac);
    }

    @Test
    void getById() {

        Long ex = p1.getId();

        PlantDTO p = dao.getById(p1.getId());
        Long ac = p.getId();

        assertEquals(ex, ac);


    }

    @Test
    void getByType() {

        int ex = 1;
        List<PlantDTO> p = dao.getByType("Rose");
        int ac = p.size();

        assertEquals(ex, ac);
    }

    @Test
    void add() {

        PlantDTO newPlant = new PlantDTO(null, "Urmom", "jo", 50000, 10);
        dao.add(newPlant);

        List<PlantDTO> plants = dao.getAll();
        int ex = 4;
        int ac = plants.size();

        assertEquals(ex, ac);
    }
}