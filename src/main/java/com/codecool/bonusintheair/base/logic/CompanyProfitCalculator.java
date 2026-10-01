package com.codecool.bonusintheair.base.logic;

import com.codecool.bonusintheair.base.data.Broker;
import com.codecool.bonusintheair.base.data.CompanyProfit;

import java.util.List;

public class CompanyProfitCalculator {
    private final BrokerSalaryCalculator brokerSalaryCalculator;

    public CompanyProfitCalculator(BrokerSalaryCalculator brokerSalaryCalculator) {
        this.brokerSalaryCalculator = brokerSalaryCalculator;
    }

    public CompanyProfit calculate(List<Broker> brokers) {
        double total = 0;
        double salaries = 0;
        for (Broker broker : brokers) {
            total += broker.profit();
            salaries += brokerSalaryCalculator.calculateSalary(broker);
        }
        return new CompanyProfit(total, salaries, total - salaries);
    }
}
