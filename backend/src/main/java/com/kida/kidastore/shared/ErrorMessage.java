package com.kida.kidastore.shared;

import lombok.*;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ErrorMessage {
    private String field;
    private String message;
    private Date timestamp;


    public ErrorMessage(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public ErrorMessage(String message) {
        this.message = message;
    }
}
