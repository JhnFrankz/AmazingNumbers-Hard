package numbers;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static int number;

    public static void main(String[] args) {

        readNumber();
        if (isNaturalNumber()) {
            checkBuzz();
        } else {
            System.out.println("This number is not natural!");
        }
    }
    public static void readNumber() {
        System.out.println("Enter a natural number: ");
        number = Integer.parseInt(scanner.nextLine());
    }

    public static boolean isNaturalNumber() {
        return number > 0;
    }

    public static void checkBuzz() {
        checkOddOrEven();

        if (endsWith7() && isDivisibleBy7()) {
            System.out.println("It is a Buzz number.\nExplanation:");
            System.out.printf("%d is divisible by 7 and ends with 7.", number);
        } else if (endsWith7()) {
            System.out.println("It is a Buzz number.\nExplanation:");
            System.out.printf("%d ends with 7.", number);
        } else if (isDivisibleBy7()) {
            System.out.println("It is a Buzz number.\nExplanation:");
            System.out.printf("%d is divisible by 7.", number);
        } else {
            System.out.println("It is not a Buzz number.\nExplanation:");
            System.out.printf("%d is neither divisible by 7 nor does it end with 7.", number);
        }
    }

    public static void checkOddOrEven() {
        if (number % 2 == 0) {
            System.out.println("This number is Even.");
        } else {
            System.out.println("This number is Odd.");
        }
    }

    public static boolean endsWith7() {
        return number % 10 == 7;
    }

    public static boolean isDivisibleBy7() {
        return number % 7 == 0;
        /*int digitDeleted = number % 10;
        int numberWithoutLastDigit = (number - digitDeleted) / 10;

        int finalNumber = numberWithoutLastDigit - digitDeleted * 2;

        return number == 7 || String.valueOf(finalNumber).substring(1).equals("7");*/
    }
}
