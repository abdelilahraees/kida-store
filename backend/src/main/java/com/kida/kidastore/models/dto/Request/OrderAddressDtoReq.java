package com.kida.kidastore.models.dto.Request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OrderAddressDtoReq {
    @NotBlank(message = "City is mandatory")
    private String city;

    @NotBlank(message = "Street is mandatory")
    private String street;

    @NotBlank(message = "PostCode is mandatory")
    private String postCode;

    @NotBlank(message = "HouseNumber is mandatory")
    private String houseNumber;

    @NotBlank(message = "Country is mandatory")
    private String country;
}
