package com.devsu.ms_cuenta_movimientos_api.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class CommonDateUtils {

    public static LocalDateTime convertToLocalDateTime(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

}
