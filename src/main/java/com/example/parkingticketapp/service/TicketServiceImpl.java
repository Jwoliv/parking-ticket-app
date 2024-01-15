package com.example.parkingticketapp.service;

import com.example.parkingticketapp.model.Parking;
import com.example.parkingticketapp.model.Ticket;
import com.example.parkingticketapp.model.User;
import com.example.parkingticketapp.service.interfaces.ParkingService;
import com.example.parkingticketapp.service.interfaces.TicketService;
import com.example.parkingticketapp.service.interfaces.UserService;
import com.example.parkingticketapp.shared.dto.TicketDto;
import com.example.parkingticketapp.shared.request.BuyTicketRequest;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Slf4j
@Service
public class TicketServiceImpl implements TicketService {
    private static final Integer ZERO = 0;
    private static final Integer LENGTH_TICKET_KEY = 48;
    private static final String SYMBOLS_TICKET_KEY = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789`~!@#$%^&*()_+-={}[]|\\:;\"'<>,.?/";

    @Setter(onMethod = @__(@Autowired))
    private UserService userService;
    @Setter(onMethod = @__(@Autowired))
    private ParkingService parkingService;
    @Setter(onMethod = @__(@Autowired))
    private Random random;

    @Override
    public ResponseEntity<TicketDto> buyTicket(BuyTicketRequest request) {
        User user = userService.findByPersonalKey(request.getPersonalKey()).orElseThrow();
        Parking parking = parkingService.findById(request.getParkingId());

        if (parking.getAvailableParkingSpaces() == 0) {
            return null; // todo throw error
        }

        Float payedMoney = request.getAmountPayedMoney();
        Float pricePerHour = parking.getPricePerHour();

        if (payedMoney > pricePerHour) {
            int paymentSumForFullHours = (int) Math.floor(payedMoney / pricePerHour);
            float change = payedMoney - paymentSumForFullHours; // todo plus this money to user

            long countPayedMinute = paymentSumForFullHours * 60L;
            LocalDateTime endTime = request.getStartTime().plusMinutes(countPayedMinute);

            updateUserChange(user, paymentSumForFullHours, change);


            Ticket ticket = Ticket.builder()
                    .numberPlace(generateNumberPlace(parking))
                    .amountPayedMoney((float) paymentSumForFullHours)
                    .startTime(request.getStartTime())
                    .endTime(endTime)
                    .key(generateTicketKey())
                    .user(user)
                    .parking(parking)
                    .build();
            // todo save ticket
        }
        return null;
    }

    private void updateUserChange(User user, float payedSum, float change) {
        float bonusMoney = payedSum * 0.1F;
        user.setBonusMoney(change);
        user.setBonusMoney(user.getBonusMoney() + bonusMoney);
        //todo save user
    }

    private Long generateNumberPlace(Parking parking) {
        List<Long> takenSeats = parking.getTickets().stream().map(Ticket::getNumberPlace).sorted().toList();
        List<Long> availableSeats = generateAvailableSeats(parking.getTotalParkingSpaces(), takenSeats);
        return availableSeats.getFirst();
    }

    private static List<Long> generateAvailableSeats(long totalParkingSpaces, List<Long> takenSeats) {
        return LongStream.rangeClosed(ZERO, totalParkingSpaces)
                .filter(number -> !takenSeats.contains(number))
                .boxed()
                .collect(Collectors.toList());
    }

    private String generateTicketKey() {
        StringBuilder ticketKey = new StringBuilder();
        int lengthSymbols = SYMBOLS_TICKET_KEY.length();
        for (int i = 0; i < LENGTH_TICKET_KEY; i++) {
            ticketKey.append(SYMBOLS_TICKET_KEY.charAt(random.nextInt(ZERO, lengthSymbols)));
        }
        return ticketKey.toString();
    }
}
