package com.rssrar.mortgage;

public class MortgageCalculator {
    private final static byte MONTH_IN_YEAR = 12;
    private final static byte PERCENT = 100;

    private final int principal;
    private final float annualInterest;
    private final byte years;

    public MortgageCalculator(int principal, float annualInterest, byte years) {
        this.principal = principal;
        this.annualInterest = annualInterest;
        this.years = years;
    }

    public double calculateBalance(short numbersOfPaymentsMade) {
        short numbersOfPayment = getNumbersOfPayment();
        float monthlyInterest = getMonthlyInterest();

        return principal
                * (Math.pow(1 + monthlyInterest, numbersOfPayment) - Math.pow(1 + monthlyInterest, numbersOfPaymentsMade))
                / (Math.pow(1 + monthlyInterest, numbersOfPayment) - 1);
    }

    public double calculateMortgage() {
        short numbersOfPayment = getNumbersOfPayment();
        float monthlyInterest = getMonthlyInterest();

        return principal
                * (monthlyInterest * Math.pow(1 + monthlyInterest, numbersOfPayment)
                / (Math.pow(1 + monthlyInterest, numbersOfPayment) - 1));
    }

    public double[] getRemainingBalances() {
        var balances = new double[getNumbersOfPayment()];
        for (short month = 1; month < balances.length; month++) {
            balances[month - 1] = calculateBalance(month);
        }
        return balances;
    }

    private short getNumbersOfPayment() {
        return (short) (years * MONTH_IN_YEAR);
    }

    private float getMonthlyInterest() {
        return annualInterest / PERCENT / MONTH_IN_YEAR;
    }
}
