package dat.controller;


import dat.dao.PlantDAO;
import dat.dto.PlantDTO;
import dat.exceptions.ApiException;
import io.javalin.http.Context;


import java.util.List;

public class PlantController implements IController {

    private PlantDAO dao;


    public PlantController(PlantDAO dao) {
        this.dao = dao;
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

        try{
            List<PlantDTO> dtos = dao.getAll();

            if (dtos != null) {
                ctx.json(dtos);
                ctx.status(200);
            }

        } catch (Exception e) {
            throw new ApiException(500, "invalid input ");
        }

    }

    @Override
    public void getPlantById(Context ctx) {

        try {
            Long id = Long.parseLong(ctx.pathParam("id"));
            PlantDTO dto = dao.getById(id);
            if (dto != null) {
                ctx.json(dto);
                ctx.status(200);
            } else {
                throw new ApiException(400, "Failed to get plant");
            }

        }catch (Exception e){
            throw new ApiException(404, "Plant with that id not found ");
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

