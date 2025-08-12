package com.nithish.fiber.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberToWordsConverter {

    private static final String[] units = {
            "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
            "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen",
            "Eighteen", "Nineteen"
    };

    private static final String[] tens = {
            "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy",
            "Eighty", "Ninety"
    };

    public static String convert(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            return "Invalid Amount";
        }

        long rupees = amount.setScale(0, RoundingMode.DOWN).longValue();
        int paise = amount.subtract(new BigDecimal(rupees))
                .multiply(new BigDecimal(100))
                .setScale(0, RoundingMode.HALF_UP)
                .intValue();

        String rupeesWords = convertToWords(rupees) + (rupees == 1 ? " Rupee" : " Rupees");
        String paiseWords = paise > 0 ? " and " + convertToWords(paise) + (paise == 1 ? " Paise" : " Paise") : "";

        return rupeesWords + paiseWords + " Only";
    }

    private static String convertToWords(long number) {
        if (number == 0) {
            return "Zero";
        }

        if (number < 20) {
            return units[(int) number];
        }

        if (number < 100) {
            return tens[(int) (number / 10)] +
                    ((number % 10 != 0) ? " " + units[(int) (number % 10)] : "");
        }

        if (number < 1000) {
            return units[(int) (number / 100)] + " Hundred" +
                    ((number % 100 != 0) ? " and " + convertToWords(number % 100) : "");
        }

        if (number < 100000) {
            return convertToWords(number / 1000) + " Thousand" +
                    ((number % 1000 != 0) ? " " + convertToWords(number % 1000) : "");
        }

        if (number < 10000000) {
            return convertToWords(number / 100000) + " Lakh" +
                    ((number % 100000 != 0) ? " " + convertToWords(number % 100000) : "");
        }

        return convertToWords(number / 10000000) + " Crore" +
                ((number % 10000000 != 0) ? " " + convertToWords(number % 10000000) : "");
    }

    // Overload for double values
    public static String convert(double amount) {
        return convert(BigDecimal.valueOf(amount));
    }
}