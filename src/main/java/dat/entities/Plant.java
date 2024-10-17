package dat.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // Entities get this tag

@Data
@NoArgsConstructor


public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // generate a id in database
    private Long id;

    private String plantType;
    private String name;
    private double price;
    private int maxHeight;

    @Builder // constructor get's build tag
    public Plant(Long id ,String plantType, String name, double price, int maxHeight) {
        this.id = id;
        this.plantType = plantType;
        this.name = name;
        this.price = price;
        this.maxHeight = maxHeight;
    }
}
