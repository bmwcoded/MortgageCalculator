package mortgage_calculator.procedural;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    final static byte MONTHS_IN_YEAR = 12;
    final static byte TO_PERCENT_OR_DECIMAL = 100;

    public static void main(String[] args) {

        String message = greetUser("Bryan", "Williamson");
        System.out.println(message);
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

        int principal = (int) (readNumber("Principal: ", 1000, 1_000_000));
        float annualInterestRate = (float) (readNumber("Annual Interest Rate: ", 1, 30));
        byte years = (byte) (readNumber("Period (Years): ", 1, 30));

        printMortgage(principal, annualInterestRate, years);
        System.out.println();
        printPaymentSchedule(principal, annualInterestRate, years);

    }

    public static String formatNumberAsCurrency(float number) {
        return NumberFormat.getCurrencyInstance().format(number);
    }

    /* no return type
        public static void greetUser(String firstName, String lastName) {
                System.out.println("Hello " + firstName + " " + lastName);
            }
         */
    public static String greetUser(String firstName, String lastName) {
        return "Hello " + firstName + " " + lastName;
    }

    public static double readNumber(String prompt, double min, double max) {
        Scanner scanner = new Scanner(System.in);
        double value;
        while (true) {
            System.out.print(prompt); // in formula r = annualInterestRate / 100
            value = scanner.nextFloat();
            if (value >= min && value <= max) {
                break;
            }
            System.out.println("Enter a value between " + min + " and " + max);
        }
        return value;
    }

    public static float calculateMortgage(int principal,
                                          float annualInterestRate,
                                          byte years) {
        float monthlyInterestRate = annualInterestRate / MONTHS_IN_YEAR / TO_PERCENT_OR_DECIMAL; // r in formula
        short numberOfPayments = (short) (years * MONTHS_IN_YEAR); // n in formula

        return (float) (principal * monthlyInterestRate * Math.pow(
                1 + monthlyInterestRate, numberOfPayments) / (Math.pow(
                1 + monthlyInterestRate, numberOfPayments) - 1));
    }

    public static float calculateBalance(int principal,
                                         float annualInterestRate,
                                         byte years,
                                         short numberOfPaymentsMade) {
        float monthlyInterestRate = annualInterestRate / MONTHS_IN_YEAR / TO_PERCENT_OR_DECIMAL; // c in formula
        short numberOfPayments = (short) (years * MONTHS_IN_YEAR); // n in formula

        return (float) (principal * (Math.pow(1 + monthlyInterestRate, numberOfPayments) -
                Math.pow(1 + monthlyInterestRate, numberOfPaymentsMade)) /
                (Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1));
    }

    private static void printMortgage(int principal,
                                      float annualInterestRate,
                                      byte years) {
        float mortgage = calculateMortgage(principal, annualInterestRate, years);

        System.out.println("Mortgage");
        System.out.println("--------");
        System.out.println("Monthly Payments: " + formatNumberAsCurrency(mortgage));
    }

    private static void printPaymentSchedule(int principal,
                                             float annualInterestRate,
                                             byte years) {
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("----------------");
        for (short month = 1; month <= years * MONTHS_IN_YEAR; month++) {
            float balance = calculateBalance(principal, annualInterestRate, years, month);
            System.out.println(formatNumberAsCurrency(balance));
        }
    }
}
