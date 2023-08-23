package com.project.customer.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponse {
    String message;
    LocalDate timeStamp;

    public ApiResponse(String message, LocalDate timeStamp) {
        this.message = message;
        this.timeStamp = timeStamp;
    }
}
