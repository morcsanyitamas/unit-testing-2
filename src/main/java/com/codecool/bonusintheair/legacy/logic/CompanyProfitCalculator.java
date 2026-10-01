package com.codecool.bonusintheair.legacy.logic;

import com.codecool.bonusintheair.base.data.BonusRule;
import com.codecool.bonusintheair.base.data.Broker;
import com.codecool.bonusintheair.base.data.CompanyProfit;

import java.util.List;

public class CompanyProfitCalculator {
    private final List<BonusRule> bis;

    public CompanyProfitCalculator(List<BonusRule> bis) {
        this.bis = bis;
    }

    public CompanyProfit calculate(List<Broker> brs) {
        int j=0;
        int i=brs.size();
        double t=0;
        double s= 0;
        double r =0;
        while(i>0){
            Broker b =brs.get(brs.size()-i);
            t+= b.profit();
            double s2=0;
            j=bis.size();
            while(j>0){
                BonusRule bo= bis.get(bis.size()-j);
                if(b.profit() >=bo.minimum()){
                    s2= bo.multiplier();
                    j =1;
                }
                j--;
            }
            s += b.baseSalary() +(b.profit()*s2);
            i--;
        }
        r= t -s;
        return new CompanyProfit(t,s,r);
    }
}
