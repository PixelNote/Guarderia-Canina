package com.example.guarderia.controller.DTO;
import lombok.*;

@AllArgsConstructor
@Data
public class ClientDTO {
    private Integer document;
    private String name;
    private String address;
    private String email;
}