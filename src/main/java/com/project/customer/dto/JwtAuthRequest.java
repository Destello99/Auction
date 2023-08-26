package com.project.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class JwtAuthRequest {
    private String username;
    private Long id;
}
