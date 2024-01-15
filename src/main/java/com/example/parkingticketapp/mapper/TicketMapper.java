package com.example.parkingticketapp.mapper;

import com.example.parkingticketapp.model.Parking;
import com.example.parkingticketapp.model.Ticket;
import com.example.parkingticketapp.model.User;
import com.example.parkingticketapp.shared.dto.TicketDto;
import com.example.parkingticketapp.shared.request.BuyTicketRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    Integer LENGTH_TICKET_KEY = 48;
    Integer MINUTE_IN_HOUR = 60;
    String SYMBOLS_TICKET_KEY = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789`~!@#$%^&*()_+-={}[]|\\:;\"'<>,.?/";
    Integer ZERO = 0;


    TicketDto entityTicketToDto(Ticket ticket);

    Ticket dtoTicketToEntity(TicketDto ticketDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "numberPlace", expression = "java(generateNumberPlace(parking))")
    @Mapping(target = "amountPayedMoney", expression = "java(generatedPayedAmountMoney(request,parking))")
    @Mapping(target = "startTime", source = "request.startTime")
    @Mapping(target = "endTime", expression = "java(generateEndTime(request,parking))")
    @Mapping(target = "key", expression = "java(generateTicketKey(random))")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "parking", source = "parking")
    Ticket generateTicketBeforeSave(BuyTicketRequest request, Parking parking, User user, Random random);


    default Long generateNumberPlace(Parking parking) {
        List<Long> takenSeats = generateTakenSeats(parking);
        List<Long> availableSeats = generateAvailableSeats(parking.getTotalParkingSpaces(), takenSeats);
        return availableSeats.getFirst();
    }

    default Integer generatedPayedAmountMoney(BuyTicketRequest request, Parking parking) {
        Float payedMoney = request.getAmountPayedMoney();
        Float pricePerHour = parking.getPricePerHour();
        return (int) Math.floor(payedMoney / pricePerHour);
    }

    default LocalDateTime generateEndTime(BuyTicketRequest request, Parking parking) {
        Integer paymentSum = generatedPayedAmountMoney(request, parking);
        Integer countMinute = generateCountPayedMinute(paymentSum);
        return request.getStartTime().plusMinutes(generateCountPayedMinute(countMinute));
    }

    default String generateTicketKey(Random random) {
        StringBuilder ticketKey = new StringBuilder();
        int lengthSymbols = SYMBOLS_TICKET_KEY.length();
        for (int i = 0; i < LENGTH_TICKET_KEY; i++) {
            ticketKey.append(SYMBOLS_TICKET_KEY.charAt(random.nextInt(ZERO, lengthSymbols)));
        }
        return ticketKey.toString();
    }

    private List<Long> generateAvailableSeats(long totalParkingSpaces, List<Long> takenSeats) {
        return LongStream.rangeClosed(ZERO, totalParkingSpaces)
                .filter(number -> !takenSeats.contains(number))
                .boxed()
                .collect(Collectors.toList());
    }

    private Integer generateCountPayedMinute(Integer paymentSum) {
        return paymentSum * MINUTE_IN_HOUR;
    }

    private List<Long> generateTakenSeats(Parking parking) {
        return parking.getTickets().stream()
                .map(Ticket::getNumberPlace)
                .sorted()
                .toList();
    }
}
