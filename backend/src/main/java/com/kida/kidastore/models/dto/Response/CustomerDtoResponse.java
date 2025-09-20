package com.kida.kidastore.models.dto.Response;

import com.kida.kidastore.models.entity.Cart;
import lombok.*;

import java.util.List;

@Builder
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDtoResponse {
    private String id;
    private String username;
    private String email;
    private String phoneNumber;
    private String imgUrl;
}
