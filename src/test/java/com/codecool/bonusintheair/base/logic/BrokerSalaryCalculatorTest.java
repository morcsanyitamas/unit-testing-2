package com.codecool.bonusintheair.base.logic;

import com.codecool.bonusintheair.base.data.BonusRule;
import com.codecool.bonusintheair.base.data.Broker;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BrokerSalaryCalculatorTest {
    List<BonusRule> bonusRules = List.of(new BonusRule(10, 1), new BonusRule(6, 0.5));
    BrokerSalaryCalculator calculator = new BrokerSalaryCalculator(bonusRules);

    @Test
    void testNoBonus() {
        Broker broker = new Broker("test", 10, 5);

        double result = calculator.calculateSalary(broker);

        double expected = 10.0;
        assertEquals(expected, result);
    }

    @Test
    void testBonus1() {
        Broker broker = new Broker("test", 10, 6);

        double result = calculator.calculateSalary(broker);

        double expected = 13.0;
        assertEquals(expected, result);
    }

    @Test
    void testBonus2() {
        Broker broker = new Broker("test", 10, 10);

        double result = calculator.calculateSalary(broker);

        double expected = 20.0;
        assertEquals(expected, result);
    }
}
