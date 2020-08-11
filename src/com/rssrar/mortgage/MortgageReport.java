package com.rssrar.mortgage;

import java.text.NumberFormat;

public class MortgageReport {

    private final MortgageCalculator calculator;
    private final NumberFormat currency;

    public MortgageReport(MortgageCalculator calculator) {
        this.calculator = calculator;
        currency = NumberFormat.getCurrencyInstance();
    }

    public void printPaymentSchedule() {
        makingSpace("PAYMENT SCHEDULE");
        for (double balance : calculator.getRemainingBalances()) {
            System.out.println(currency.format(balance));
        }
    }

    private void makingSpace(String s) {
        System.out.println();
        System.out.println(s);
        System.out.println("---------");
    }

    public void printMortgage() {
        double mortgage = calculator.calculateMortgage();
        String mortgageFormatted = currency.format(mortgage);
        makingSpace("MORTGAGE");
        System.out.println("Monthly Payments: " + mortgageFormatted);
    }
}
