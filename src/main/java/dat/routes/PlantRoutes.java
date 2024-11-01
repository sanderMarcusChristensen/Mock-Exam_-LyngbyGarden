package dat.routes;

import dat.config.HibernateConfig;
import dat.controller.PlantController;
import dat.dao.PlantDAO;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManagerFactory;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.post;


public class PlantRoutes {

    private final EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("lyngbygarden");
    private final PlantDAO dao = new PlantDAO(emf);
    private final PlantController plantController = new PlantController(dao);

    public EndpointGroup getPlantRoutes() {
        return () -> {
            get("/", plantController::getAllPlants);
            get("/{id}", plantController::getPlantById);
            get("/type/{type}", plantController::getPlantByType);
            post("/", plantController::addPlant);
        };

    }


}
