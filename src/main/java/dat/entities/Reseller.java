package dat.entities;

import dat.dto.ResellerDTO;
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
public class Reseller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate ID
    private Long id;            // Unique identifier
    private String name;        // Name of the reseller
    private String address;     // Address of the reseller
    private String phone;       // Phone number of the reseller

    @ManyToMany(cascade = CascadeType.ALL) // Bidirectional many-to-many relationship
    @JoinTable(
            name = "reseller_plant",  // Join table name
            joinColumns = @JoinColumn(name = "reseller_id"), // Reseller column
            inverseJoinColumns = @JoinColumn(name = "plant_id") // Plant column
    )
    private List<Plant> plants = new ArrayList<>(); // Initialize with an empty list

    // Constructor to create Reseller from ResellerDTO
    public Reseller(ResellerDTO dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.address = dto.getAddress();
        this.phone = dto.getPhone();
    }
}
