package dat.entities;

import dat.dto.PlantDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate ID
    private Long id;            // Unique identifier
    private String plantType;   // Type of the plant
    private String name;        // Name of the plant
    private double price;       // Price of the plant
    private int maxHeight;      // Maximum height of the plant in cm

    @ManyToMany(mappedBy = "plants", cascade = CascadeType.ALL) // Bidirectional many-to-many relationship
    private List<Reseller> resellers = new ArrayList<>();

    // Constructor to create Plant from PlantDTO
    public Plant(PlantDTO dto) {
        this.id = dto.getId();
        this.plantType = dto.getPlantType();
        this.name = dto.getName();
        this.maxHeight = dto.getMaxHeight();
        this.price = dto.getPrice();
    }
}
