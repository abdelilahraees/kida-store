package com.kida.kidastore.models.dto.Response;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@ToString
public class AddressDtoResponse {
    private Long id;
    private String city;
    private String street;
    private String houseNumber;
}
