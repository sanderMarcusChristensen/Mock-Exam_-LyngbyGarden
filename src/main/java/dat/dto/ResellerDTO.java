package dat.dto;

import dat.entities.Reseller;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class ResellerDTO {

    private Long id;
    private String name;
    private String address;
    private String phone;

    @Builder
    public ResellerDTO (Long id, String name, String address, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public Reseller getResellerAsEntity(){

        return Reseller.builder()
                .id(id)
                .address(address)
                .phone(phone)
                .name(name)
                .build();
    }
}
