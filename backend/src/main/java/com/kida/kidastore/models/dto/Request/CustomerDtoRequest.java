package com.kida.kidastore.models.dto.Request;

import lombok.*;


@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDtoRequest {
    private String id;
    private String username;
    private String email;
    private String phoneNumber;
    private String imgUrl;
}
