package mortgage_calculator.oop;

import java.text.NumberFormat;

    public class MortgageReport {
        private final NumberFormat currency;
        private MortgageCalculator mortgageCalculator;

        public MortgageReport(MortgageCalculator mortgageCalculator) {
            this.mortgageCalculator = mortgageCalculator;
            currency = NumberFormat.getCurrencyInstance();
        }

        public void printMortgage() {
            String mortgageFormatted = currency.format(mortgageCalculator.calculateMortgage());
            System.out.println();
            System.out.println("MORTGAGE");
            System.out.println("--------");
            System.out.println("Monthly Payments: " + mortgageFormatted);
        }

        public void printPaymentSchedule() {
            System.out.println();
            System.out.println("PAYMENT SCHEDULE");
            System.out.println("----------------");
            double[] residualBalances = mortgageCalculator.getResidualBalances();
            for (int i = 0; i < residualBalances.length; i++) {
                double balance = residualBalances[i];
                String balanceFormatted = currency.format(balance);
                System.out.println(balanceFormatted);
            }
//        for (double balance : residualBalances) {
//            System.out.println(currency.format(balance));
//        }
        }
    }

