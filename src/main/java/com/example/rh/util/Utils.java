package com.example.rh.util;


import java.math.BigDecimal;

/**
 *
 * @author tiavi
 */
public class Utils {
    public static double round(double number) {
        BigDecimal db = new BigDecimal(number);
        db = db.setScale(2, BigDecimal.ROUND_HALF_UP);
        return db.doubleValue();
    }
}
