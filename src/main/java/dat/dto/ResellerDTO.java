package dat.dto;

import dat.entities.Reseller;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResellerDTO {
    private Long id;      // Unique identifier for the reseller
    private String name;  // Name of the reseller
    private String address; // Address of the reseller
    private String phone; // Phone number of the reseller

    // Constructor to create ResellerDTO from a Reseller entity
    public ResellerDTO(Reseller entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.address = entity.getAddress();
        this.phone = entity.getPhone();
    }
}
