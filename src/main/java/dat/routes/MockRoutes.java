package dat.routes;

import dat.config.HibernateConfig;
import dat.controller.MockController;
import dat.controller.PlantController;
import dat.dao.PlantDAO;
import dat.dao.PlantDAOMock;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManagerFactory;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.post;

public class MockRoutes {

       // private final EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("lyngbygarden");
        private final PlantDAOMock dao = new PlantDAOMock();
        private final MockController plantController = new MockController(dao);

        public EndpointGroup getMockPlantRoutes() {
            return () -> {
                get("/", plantController::getAllPlants);
                get("/{id}", plantController::getPlantById);
                get("/type/{type}", plantController::getPlantByType);
                post("/", plantController::addPlant);
            };

        }
}
