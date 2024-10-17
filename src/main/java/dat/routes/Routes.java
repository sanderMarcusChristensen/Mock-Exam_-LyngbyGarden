package dat.routes;

import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.path;

public class Routes {

    private final PlantRoutes plantRoutes = new PlantRoutes();

    public EndpointGroup getApiRoutes() {

        return () -> {
            path("/plant", plantRoutes.getPlantRoutes());
        };
    }
}
