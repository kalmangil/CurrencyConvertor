import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Coin calculator = null;

        List<String> totalResults = new ArrayList<String>();
        // First screen
        System.out.println("Welcome to currency converter");

        // for checking Y/N enterd by the user
        boolean shouldCalculate = true;
        while (shouldCalculate) {

            System.out.println("Please choose an option (1/2): ");
            System.out.println("1. Dollars to Shekels");
            System.out.println("2. Shekels to Dollars");
            System.out.println("3. Shekels to Euros");

            // for checking valid user input 1/2/3
            boolean isSelectionCorrect = false;

            Coins fromCurrency = null;
            Coins toCurrency = null;

            while (!isSelectionCorrect) {
                try {
                    isSelectionCorrect = true;
                    int selection = sc.nextInt();

                    if (selection == 1) {
                        calculator = CoinsFactory.getCoinInstance(Coins.USD);
                        fromCurrency = Coins.USD;
                        toCurrency = Coins.ILS;
                    } else if (selection == 2) {
                        calculator = CoinsFactory.getCoinInstance(Coins.ILS);
                        fromCurrency = Coins.ILS;
                        toCurrency = Coins.USD;
                    } else if (selection == 3) {
                        calculator = CoinsFactory.getCoinInstance(Coins.EUR);
                        fromCurrency = Coins.EUR;
                        toCurrency = Coins.ILS;
                    } else {
                        System.out.println("Wrong choice, choose again");
                        isSelectionCorrect = false;
                    }
                } catch (Exception e) {
                    System.out.println("Exception detected, please enter a valid number");
                    isSelectionCorrect = false;
                    sc = new Scanner(System.in);
                }
            }
            // Second screen
            System.out.println("Please enter an amount to convert");
            double amount = sc.nextDouble();

            double result = calculator.calculate(amount);
            System.out.println("result is " + result);

            // Adding result to totalResults List
            String niceOutput = createString (amount, fromCurrency, result, toCurrency);
            totalResults.add(niceOutput);

            // Check whether to proceed to another calculation
            System.out.println("Please enter Y / N if you want another calculation");
            Pattern pattern = Pattern.compile("[YNyn]");

            try {
                String election = sc.next(pattern);
                if (election.equals("N") || election.equals("n")){
                    shouldCalculate = false;
                }
            } catch (Exception e) {
                System.out.println("You have chosen poorly - GOODBYE");
                shouldCalculate = false;
            }
        }

        // Fourth screen
        System.out.println("Thanks for using our currency converter");

        //Print output list
        System.out.println(totalResults);

        // Write list to a File
        try {
            Files.write(Paths.get("C:\\Users\\Idan\\Desktop\\CurrencyConverter.txt"), totalResults);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Generate a printable string for presenting an output
    public static String createString (double fromAmount, Coins fromCoinName, double toAmount, Coins toCoinName){
        return "For: " + fromAmount + " " + fromCoinName + " you need: " + toAmount + " " + toCoinName;

    }
}
