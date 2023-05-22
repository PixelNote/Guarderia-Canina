package com.example.guarderia.service.rabbitmq;

import com.example.guarderia.model.Booking;
import com.example.guarderia.repository.BookingRepository;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Component
@EnableRabbit
public class SendBookingService {

    private final RabbitTemplate rabbitTemplate;

    private final BookingRepository bookingRepository;

    public SendBookingService(RabbitTemplate rabbitTemplate, BookingRepository bookingRepository){
        this.rabbitTemplate = rabbitTemplate;
        this.bookingRepository = bookingRepository;
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
    }

    @Scheduled(fixedRate = 5000)
    public void sendBooking(){

        List<Booking> bookingList = bookingRepository.findBookingsByDateAndDelivered(LocalDate.now(), "false");
        if(!bookingList.isEmpty()) {
            bookingList.forEach(booking -> {
                if (LocalTime.now().isAfter(booking.getTime()) && booking.getDelivered().equals("false")) {
                    rabbitTemplate.convertAndSend("notificationExchange", "message",
                            new NotificationDTO(
                                    booking.getPet().getClient().getName(),
                                    booking.getPet().getClient().getEmail(),
                                    booking.getPet().getName(),
                                    booking.getDate(),
                                    booking.getTime()
                            ));

                    booking.setDelivered("true");
                    bookingRepository.save(booking);
                }
            });
        }





    }
}
