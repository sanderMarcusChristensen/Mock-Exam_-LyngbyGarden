package dat.controller;

import dat.config.HibernateConfig;
import dat.dao.PlantDAOMock;
import dat.dto.PlantDTO;
import dat.exceptions.ApiException;
import io.javalin.http.Context;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public class MockController implements IController{

    public PlantDAOMock dao;
    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("lyngbygarden");

    public MockController(PlantDAOMock dao) {
        this.dao = PlantDAOMock.getInstance(emf);
    }

    @Override
    public void addPlant(Context ctx) {

        PlantDTO newPlant = ctx.bodyAsClass(PlantDTO.class); // Parse JSON into PlantDTO
        PlantDTO addedPlant = dao.add(newPlant);

        if (addedPlant.getName().isEmpty()) {
            throw new ApiException(400, "Plant name is empty");
        }

        if (addedPlant.getPlantType().isEmpty()) {
            throw new ApiException(400, "Plant type is empty");
        }

        if (addedPlant.getMaxHeight() == 0) {
            throw new ApiException(400, "Max height is empty");
        }

        if (addedPlant.getPrice() == 0){
            throw new ApiException(400, "Price is empty");
        }

        ctx.res().setStatus(201);
        ctx.json(addedPlant, PlantDTO.class);

    }

    @Override
    public void getAllPlants(Context ctx) {

        List<PlantDTO> plants = dao.getAll();

        if (plants.isEmpty()){
            ctx.status(404);
            throw new EntityNotFoundException("No plants found");
        }
        ctx.json(plants);
        ctx.status(200);
    }


    @Override
    public void getPlantById(Context ctx) {

        int id = ctx.pathParamAsClass("id", Integer.class).check(this::validatePrimaryKey, "Not a valid id").get();
        PlantDTO plant = dao.getById(id);
        ctx.res().setStatus(200);
        ctx.json(plant, PlantDTO.class);

    }

    @Override
    public void getPlantByType(Context ctx) {

        String type = ctx.pathParam("type");
        List<PlantDTO> plants = dao.getByType(type);
        if (!plants.isEmpty()) {
            ctx.json(plants);
            ctx.status(200); // OK
        } else {
            throw  new ApiException(404, "plant with that plantType not found");
        }

    }

    @Override
    public void getShortPlants(Context ctx) {

    }

    @Override
    public void getSortedPlants(Context ctx) {

    }

    @Override
    public void getPlantNames(Context ctx) {

    }

    public boolean validatePrimaryKey(Integer integer) {
        return dao.validatePrimaryKey(integer);
    }
}
