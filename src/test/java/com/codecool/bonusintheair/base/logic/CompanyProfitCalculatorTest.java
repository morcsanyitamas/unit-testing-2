package com.codecool.bonusintheair.base.logic;

import com.codecool.bonusintheair.base.data.BonusRule;
import com.codecool.bonusintheair.base.data.Broker;
import com.codecool.bonusintheair.base.data.CompanyProfit;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CompanyProfitCalculatorTest {
    List<BonusRule> bonusRules = List.of(new BonusRule(10, 1), new BonusRule(6, 0.5));
    BrokerSalaryCalculator brokerSalaryCalculator = new BrokerSalaryCalculator(bonusRules);
    CompanyProfitCalculator companyProfitCalculator = new CompanyProfitCalculator(brokerSalaryCalculator);

    @Test
    void testZeroProfit() {
        List<Broker> brokers = List.of();

        CompanyProfit companyProfit = companyProfitCalculator.calculate(brokers);

        assertEquals(0, companyProfit.total());
        assertEquals(0, companyProfit.salaries());
        assertEquals(0, companyProfit.remaining());
    }

    @Test
    void testZeroBonus() {
        List<Broker> brokers = List.of(
                new Broker("test1", 1, 5),
                new Broker("test2", 1, 4)
        );

        CompanyProfit companyProfit = companyProfitCalculator.calculate(brokers);

        assertEquals(9, companyProfit.total());
        assertEquals(2, companyProfit.salaries());
        assertEquals(7, companyProfit.remaining());
    }

    @Test
    void testBonus() {
        List<Broker> brokers = List.of(
                new Broker("test1", 1, 6),
                new Broker("test2", 1, 10)
        );

        CompanyProfit companyProfit = companyProfitCalculator.calculate(brokers);

        assertEquals(16, companyProfit.total());
        assertEquals(15, companyProfit.salaries());
        assertEquals(1, companyProfit.remaining());
    }
}
