package com.codecool.bonusintheair.base.logic;

import com.codecool.bonusintheair.base.data.BonusRule;
import com.codecool.bonusintheair.base.data.Broker;

import java.util.List;

public class BrokerSalaryCalculator {
    private final List<BonusRule> bonusRules;

    public BrokerSalaryCalculator(List<BonusRule> bonusRules) {
        this.bonusRules = bonusRules;
    }

    public double calculateSalary(Broker broker) {
        return broker.baseSalary() + calculateBonus(broker.profit());
    }

    private double calculateBonus(double profit) {
        return profit * getBonusMultiplier(profit);
    }

    private double getBonusMultiplier(double profit) {
        for (BonusRule bonusRule : bonusRules) {
            if (profit >= bonusRule.minimum()) {
                return bonusRule.multiplier();
            }
        }
        return 0;
    }
}
