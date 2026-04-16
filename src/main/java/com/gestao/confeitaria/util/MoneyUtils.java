package com.gestao.confeitaria.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MoneyUtils {

    private MoneyUtils() {
        // evita instanciar
    }

    public static BigDecimal scale(BigDecimal value) {
        return value.setScale(2, RoundingMode.HALF_UP);
    }
}