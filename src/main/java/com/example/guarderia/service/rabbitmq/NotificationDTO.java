package com.example.guarderia.service.rabbitmq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDTO {

    String client;
    String email;
    String pet;
    LocalDate date;
    LocalTime time;





}
