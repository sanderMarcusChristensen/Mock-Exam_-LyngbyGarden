package dat.controller;

import dat.dao.PlantDAOMock;
import dat.dto.PlantDTO;
import dat.exceptions.ApiException;
import io.javalin.http.Context;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public class MockController implements IController{

    public PlantDAOMock dao;

    public MockController(PlantDAOMock dao) {
        this.dao = dao;
    }

    @Override
    public void addPlant(Context ctx) {

        PlantDTO newPlant = ctx.bodyAsClass(PlantDTO.class); // Parse JSON into PlantDTO

        if( newPlant.getPlantType().isEmpty()){
            throw new IllegalArgumentException("Plant type cannot be empty");
        }

        if( newPlant.getMaxHeight() == 0){
            throw new IllegalArgumentException("Plant max height cannot be 0");
        }

        if( newPlant.getPrice() == 0){
            throw new IllegalArgumentException("Plant pris cannot be 0");
        }

        if( newPlant.getName() == null){
            throw new IllegalArgumentException("Plant name cannot be null");
        }
        PlantDTO addedPlant = dao.add(newPlant);
        ctx.json(addedPlant);
        ctx.status(201); // Created
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

        int id = Integer.parseInt(ctx.pathParam("id"));
        PlantDTO plant = dao.getById(id);
        if (plant != null) {
            ctx.json(plant);
            ctx.status(200); // OK
        } else {
            throw new ApiException(404,"plant with that id  not found");
        }
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
}
