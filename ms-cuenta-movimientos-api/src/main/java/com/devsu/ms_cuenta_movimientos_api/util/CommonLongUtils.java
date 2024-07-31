package com.devsu.ms_cuenta_movimientos_api.util;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Set;

public class CommonLongUtils {

    private static final SecureRandom random = new SecureRandom();
    private static final Set<Long> generatedNumbers = new HashSet<>();

    public static long generateUniqueRandomNumber() {
        while (true) {
            long randomNumber = (long) (random.nextDouble() * 1_000_000_000_000_000_000L);
            if (generatedNumbers.add(randomNumber)) return randomNumber;
        }
    }

}
