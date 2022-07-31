package numbers;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static int number;
    private static boolean isEven;
    private static boolean isOdd;
    private static boolean isBuzz;
    private static boolean isDuck;

    public static void main(String[] args) {
        start();
    }
    public static void start() {
        readNumber();

        if (isNaturalNumber()) {
            checkProperties();
            showProperties();
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

    public static void checkProperties() {
        isOddOrEven();
        isBuzz();
        isDuck();
    }

    public static void isOddOrEven() {
        if (number % 2 == 0) {
            isEven = true;
        } else {
            isOdd = true;
        }
    }

    public static void isBuzz() {
        isBuzz = endsWith7() || isDivisibleBy7();
    }

    public static boolean endsWith7() {
        return number % 10 == 7;
    }

    public static boolean isDivisibleBy7() {
        return number % 7 == 0;
    }

    public static void isDuck() {
        isDuck = Integer.toString(number).contains("0");
    }

    public static void showProperties() {
        System.out.printf("Properties of %d\n" +
                "\teven: %b\n" +
                "\todd: %b\n" +
                "\tbuzz: %b\n" +
                "\tduck: %b\n", number, isEven, isOdd, isBuzz, isDuck);
    }
}
