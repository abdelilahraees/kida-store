package com.kida.kidastore.models.dto.Request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@ToString
public class AddressDtoRequest {
    @NotBlank(message = "city must be null for request")
    private String city;
    @NotBlank(message = "street must be null for request")
    private String street;
    private String houseNumber;
}
