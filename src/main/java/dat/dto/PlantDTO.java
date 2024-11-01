package dat.dto;


import dat.entities.Plant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlantDTO {
    private Long id;         // Unique identifier for the plant
    private String plantType; // Type of the plant (e.g., Rose, Bush, etc.)
    private String name;     // Name of the plant
    private int maxHeight;   // Maximum height of the plant in cm
    private double price;     // Price of the plant

    // Constructor to create PlantDTO from a Plant entity
    public PlantDTO(Plant entity) {
        this.id = entity.getId();
        this.plantType = entity.getPlantType();
        this.name = entity.getName();
        this.maxHeight = entity.getMaxHeight();
        this.price = entity.getPrice();
    }
}
