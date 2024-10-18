package dat.controller;


import dat.dao.PlantDAO;
import dat.dto.PlantDTO;
import dat.exceptions.ApiException;
import io.javalin.http.Context;


import java.util.List;

public class PlantController implements IController {

    private PlantDAO dao;
    private String plantType = "Rose";

    public PlantController(PlantDAO dao) {
        this.dao = dao;
    }

    @Override
    public void addPlant(Context ctx) {

        try {
            PlantDTO dto = ctx.bodyAsClass(PlantDTO.class);
            PlantDTO savedDTO = dao.add(dto);

            if (savedDTO != null) {
                ctx.status(200);
                ctx.json(savedDTO);
            } else {
                throw new ApiException(400, "Failed to add/create a new plant");
            }
        } catch (Exception e) {
            throw new ApiException(500, "invalid input ");
        }
    }

    @Override
    public void getAllPlants(Context ctx) {

        try{
            List<PlantDTO> dtos = dao.getAll();

            if (dtos != null) {
                ctx.json(dtos);
                ctx.status(200);
            } else{
                throw new ApiException(400, "Failed to get all plants");
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
            throw new ApiException(500, "invalid input ");
        }


    }

    @Override
    public void getPlantByType(Context ctx) {

        try {
            List<PlantDTO> plants = dao.getByType(plantType);

            if (plants != null) {
                ctx.json(plants);
                ctx.status(200);
            } else{
                throw new ApiException(400, "Failed to get plant");
            }
        } catch (Exception e){
            throw new ApiException(500, "invalid input ");
        }
    }

    @Override
    public void getShortPlants(Context ctx) {

        try{
            List<PlantDTO> dtos = dao.shortPlants();

            if(dtos != null){
                ctx.json(dtos);
                ctx.status(200);
            } else {
                throw new ApiException(400, "Failed to get all plants");
            }
        } catch (Exception e){
            throw new ApiException(500, "invalid input ");
        }

    }

    @Override
    public void getSortedPlants(Context ctx) {

        try{
            List<PlantDTO> dtos = dao.sortedPlants();
            if(dtos != null){
                ctx.json(dtos);
                ctx.status(200);
            } else{
                throw new ApiException(400, "Failed to get all plants");
            }
        } catch (Exception e){
            throw new ApiException(500, "invalid input ");
        }

    }

    @Override
    public void getPlantNames(Context ctx) {
        try{
            List<String> names = dao.plantNames();
            if(names != null){
                ctx.json(names);
                ctx.status(200);

            } else{
                throw new ApiException(400, "Failed to get all plants");
            }
        } catch (Exception e){
            throw new ApiException(500, "invalid input ");
        }

    }


}

