package mortgage_calculator.oop;

/* Calculating Mortgage
        M = P * r * ((1 + r)^n) / ((1 + r)^(n) - 1)
        - M is your monthly payment
        - P is your principal
        - r is your monthly interest rate, calculated by dividing your annual interest rate by 12
        - n is your number of payments (the number of months you will be paying the loan
         */

        /* Calculating Balance
        B = L * [(1 + c)^n - (1 + c)^p] / [(1 + c)^n - 1]
        - L is the loan amount
        - p is # of payments made
        - n is the month when the balance is paid in full
         */

public class MortgageCalculator {
    private final static byte MONTHS_IN_YEAR = 12;
    private final static byte PERCENT = 100;

    private int principal;
    private float annualInterest;
    private byte years;

    public MortgageCalculator(int principal, float annualInterest, byte years) {
        this.principal = principal;
        this.annualInterest = annualInterest;
        this.years = years;
    }

    public double calculateMortgage() {
        float monthlyInterest = getMonthlyInterest();
        float numberOfPayments = getNumberOfPayments();

        return principal *
                (monthlyInterest * Math.pow(1 + monthlyInterest, numberOfPayments))
                /
                (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);


    }

    public double calculateBalance(short numberOfPaymentsMade) {
        float monthlyInterest = getMonthlyInterest();
        float numberOfPayments = getNumberOfPayments();

        return principal *
                (Math.pow(1 + monthlyInterest, numberOfPayments) -
                        Math.pow(1 + monthlyInterest, numberOfPaymentsMade))
                /
                (Math.pow(1 + monthlyInterest, numberOfPayments) - 1);
    }

    public double[] getResidualBalances() {
        double[] balances = new double[getNumberOfPayments()];
        for (short month = 1; month <= getNumberOfPayments(); month++) {
            balances[month - 1] = calculateBalance(month);
        }
        return balances;
    }

    private int getNumberOfPayments() {
        return years * MONTHS_IN_YEAR;
    }

    private float getMonthlyInterest() {
        return annualInterest / PERCENT / MONTHS_IN_YEAR;
    }
}
