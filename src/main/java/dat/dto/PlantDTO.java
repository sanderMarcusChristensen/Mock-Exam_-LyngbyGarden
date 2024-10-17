package dat.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dat.entities.Plant;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// lombok to make getter and setter
@Data
@NoArgsConstructor
@AllArgsConstructor

@NamedQueries({
        @NamedQuery(name = "Plant_findPlantType", query = "SELECT p FROM Plant p Where p.plantType = :type")
})


public class PlantDTO {

    private Long id ;
    private String plantType;
    private String name;
    private double price;
    private int maxHeight;

    @Builder
    public PlantDTO(Plant plant){
        this.id = plant.getId();
        this.plantType = plant.getPlantType();
        this.name = plant.getName();
        this.price = plant.getPrice();
        this.maxHeight = plant.getMaxHeight();
    }


    @JsonIgnore
    public Plant getPlantAsEntity(){
        return Plant.builder()
                .id(id)
                .maxHeight(maxHeight)
                .plantType(plantType)
                .price(price)
                .name(name)
                .build();
    }





}
