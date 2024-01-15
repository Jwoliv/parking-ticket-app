package com.example.parkingticketapp.utils;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class UniqueKeyGenerator {
    private static final String SYMBOLS_TICKET_KEY = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789`~!@#$%^&*()_+-={}[]|\\:;\"'<>,.?/";
    private static final Integer LENGTH_TICKET_KEY = 48;
    private static final  Integer ZERO = 0;

    @Setter(onMethod = @__(@Autowired))
    private static Random random = new Random();

    public static String generateTicketKey() {
        StringBuilder ticketKey = new StringBuilder();
        int lengthSymbols = SYMBOLS_TICKET_KEY.length();
        for (int i = 0; i < LENGTH_TICKET_KEY; i++) {
            ticketKey.append(SYMBOLS_TICKET_KEY.charAt(random.nextInt(ZERO, lengthSymbols)));
        }
        return ticketKey.toString();
    }
}
