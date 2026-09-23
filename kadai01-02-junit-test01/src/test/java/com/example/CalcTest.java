package com.example;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CalcTest {

    private static Calc calc;

    @BeforeAll
    static void テスト前処理() {
        calc = new Calc();
    }

    @Test
    void subテスト_正常() {
        assertThat(calc.sub(10, 3)).isEqualTo(7);
    }

    @Test
    void divテスト_正常() {
        assertThat(calc.div(12, 3)).isEqualTo(4);
    }

    @Test
    void multテスト_正常() {
        assertThat(calc.mult(4, 5)).isEqualTo(20);
    }

    @Test
    void divテスト_異常_0除算() {
        assertThatThrownBy(() -> calc.div(5, 0))
                .isInstanceOf(ArithmeticException.class)
                .hasMessageContaining("by zero");
    }

    @AfterAll
    static void テスト後処理() {
        calc = null;
    }
}
