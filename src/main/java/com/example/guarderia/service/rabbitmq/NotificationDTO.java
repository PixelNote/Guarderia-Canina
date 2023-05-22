package com.example.guarderia.service.rabbitmq;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
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
    @JsonSerialize(using = LocalDateSerializer.class)
    LocalDate date;
    @JsonSerialize(using = LocalTimeSerializer.class)
    LocalTime time;





}
