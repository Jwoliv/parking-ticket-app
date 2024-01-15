package com.example.parkingticketapp.mapper;

import com.example.parkingticketapp.model.Parking;
import com.example.parkingticketapp.model.Ticket;
import com.example.parkingticketapp.model.User;
import com.example.parkingticketapp.shared.dto.TicketDto;
import com.example.parkingticketapp.shared.request.BuyTicketRequest;
import com.example.parkingticketapp.utils.UniqueKeyGenerator;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Mapper(componentModel = "spring", uses = {UserMapper.class, AddressMapper.class, ParkingMapper.class})
public interface TicketMapper {
    Integer MINUTE_IN_HOUR = 60;
    Integer ZERO = 0;
    Float TEN_PERCENTAGE = 0.1F;


    TicketDto entityTicketToDto(Ticket ticket);

    Ticket dtoTicketToEntity(TicketDto ticketDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "numberPlace", expression = "java(generateNumberPlace(parking))")
    @Mapping(target = "amountPayedMoney", expression = "java(generatedPayedAmountMoney(request, parking))")
    @Mapping(target = "amountBonusMoney", expression = "java(generateBonusMoney(request, parking))")
    @Mapping(target = "startTime", source = "request.startTime")
    @Mapping(target = "endTime", expression = "java(generateEndTime(request, parking))")
    @Mapping(target = "key", expression = "java(generateTicketKey())")
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

    default String generateTicketKey() {
        return UniqueKeyGenerator.generateTicketKey();
    }

    default Float generateBonusMoney(BuyTicketRequest request, Parking parking) {
        Integer amountBonusMoney = generatedPayedAmountMoney(request, parking);
        return amountBonusMoney * TEN_PERCENTAGE;
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
