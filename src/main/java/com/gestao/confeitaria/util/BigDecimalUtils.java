package com.gestao.confeitaria.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Classe utilitária para operações seguras com BigDecimal.
 *
 * Objetivo:
 * - Evitar erros comuns (divisão por zero, null, dízima infinita)
 * - Padronizar arredondamentos no sistema
 * - Centralizar lógica matemática reutilizável
 *
 * Observação:
 * Esta classe não deve conter regras de negócio,
 * apenas operações matemáticas genéricas.
 */
public final class BigDecimalUtils {

    /**
     * Construtor privado para evitar instância da classe.
     * Esta é uma classe utilitária (somente métodos estáticos).
     */
    private BigDecimalUtils() {
    }

    /**
     * Retorna zero como BigDecimal.
     *
     * Uso:
     * - Evitar repetir BigDecimal.ZERO no código
     * - Melhorar legibilidade
     */
    public static BigDecimal zero() {
        return BigDecimal.ZERO;
    }

    /**
     * Aplica escala padrão de 2 casas decimais (ex: moeda).
     *
     * Regras:
     * - Se o valor for null, retorna ZERO
     * - Arredondamento HALF_UP (padrão financeiro)
     *
     * Exemplo:
     * 10.456 -> 10.46
     * 10.454 -> 10.45
     */
    public static BigDecimal scale(BigDecimal value) {
        if (value == null) {
            return BigDecimal.ZERO;
        }

        return value.setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Realiza divisão segura entre dois valores.
     *
     * Problemas que resolve:
     * - Evita ArithmeticException (dízima infinita)
     * - Evita divisão por zero
     * - Evita NullPointerException
     *
     * Regras:
     * - Se algum valor for null -> retorna ZERO
     * - Se divisor for zero -> retorna ZERO
     * - Usa escala 4 para manter precisão interna
     *
     * Exemplo:
     * 10 / 3 -> 3.3333
     */
    public static BigDecimal divide(BigDecimal a, BigDecimal b) {
        if (a == null || b == null || b.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }

        return a.divide(b, 4, RoundingMode.HALF_UP);
    }

    /**
     * Multiplicação segura entre dois valores.
     *
     * Regras:
     * - Se algum valor for null -> retorna ZERO
     * - Mantém escala 4 para precisão interna
     *
     * Exemplo:
     * 2.5 * 3 -> 7.5000
     */
    public static BigDecimal multiply(BigDecimal a, BigDecimal b) {
        if (a == null || b == null) {
            return BigDecimal.ZERO;
        }

        return a.multiply(b).setScale(4, RoundingMode.HALF_UP);
    }
}