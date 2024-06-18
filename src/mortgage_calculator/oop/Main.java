package mortgage_calculator.oop;

public class Main {
    final static byte MONTHS_IN_YEAR = 12;
    final static byte TO_PERCENT_OR_DECIMAL = 100;

    public static void main(String[] args) {

        String message = greetUser("Bryan", "Williamson");
        System.out.println(message);

        int principal = (int) Console.readNumber("Principal: ", 1000, 1_000_000);
        float annualInterest = (float) Console.readNumber("Annual Interest Rate: ", 1, 30);
        byte years = (byte) Console.readNumber("Period (Years): ", 1, 30);

        MortgageCalculator mortgageCalculator = new MortgageCalculator(principal, annualInterest, years);

        MortgageReport mortgageReport = new MortgageReport(mortgageCalculator);
        mortgageReport.printMortgage();
        mortgageReport.printPaymentSchedule();

    }

    public static String greetUser(String firstName, String lastName) {
        return "Hello " + firstName + " " + lastName;
    }
}
