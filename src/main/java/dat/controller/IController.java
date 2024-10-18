package dat.controller;

import io.javalin.http.Context;

public interface IController {

    public void addPlant(Context ctx);
    public void getAllPlants(Context ctx);
    public void getPlantById(Context ctx);
    public void getPlantByType(Context ctx);
    public void getShortPlants(Context ctx);
    public void getSortedPlants(Context ctx);
    public void getPlantNames(Context ctx);



}
