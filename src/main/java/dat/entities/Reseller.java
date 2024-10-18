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


public class Reseller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String phone;

    @OneToMany
    @JoinTable(
            name = "reseller_plant",
            joinColumns = @JoinColumn(name = "reseller_id"),
            inverseJoinColumns = @JoinColumn(name = "plant_id")
    )
    private List<Plant> plants;

    @Builder
    public Reseller(Long id, String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.id = id;
    }

    public ResellerDTO getResellerAsDTO(){
        return ResellerDTO.builder()
                .id(id)
                .name(name)
                .address(address)
                .phone(phone)
                .build();
    }

}
