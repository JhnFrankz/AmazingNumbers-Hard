package numbers;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static int numParemeters;
    private static long[] numbers;
    private static boolean even;
    private static boolean odd;
    private static boolean buzz;
    private static boolean duck;
    private static boolean palindromic;
    private static boolean gapful;

    public static void main(String[] args) {
        showWelcome();
        start();
    }
    public static void start() {
        readNumber();

        if (numParemeters == 1) {
            if (isNaturalNumber(numbers[0])) {
                checkProperties();
                showProperties();
            } else if (numbers[0] == 0) {
                System.out.println("""
                    
                    Goodbye!""");
                return;
            } else if (numbers[0] < 0) {
                System.out.println("The first parameter should be a natural number or zero.");
            }
        } else {
            if (isNaturalNumber(numbers[0]) && isNaturalNumber(numbers[1])) {
                long a = numbers[0];

                System.out.println();
                for (long i = numbers[0]; i < a + numbers[1]; i++) {
                    checkProperties(i);
                    showProperties(i);
                }
                System.out.println();

            } else if (numbers[0] < 0) {
                System.out.println("The first parameter should be a natural number or zero.");
            } else if (numbers[1] < 0) {
                System.out.println("The second parameter should be a natural number.");
            }
        }



        start();
    }

    public static void showWelcome() {
        System.out.println("""
                Welcome to Amazing Numbers!

                Supported requests:
                - enter a natural number to know its properties;
                - enter two natural numbers to obtain the properties of the list:
                  * the first parameter represents a starting number;
                  * the second parameter shows how many consecutive numbers are to be processed;
                - separate the parameters with one space;
                - enter 0 to exit.
                """);
    }

    public static void readNumber() {
        System.out.print("Enter a request: ");
        String[] input1 = scanner.nextLine().split(" ");

        if (input1.length > 1) {
            numParemeters = 2;
            numbers = new long[2];

            for (int i = 0; i < numbers.length; i++) {
                try {
                    numbers[i] = Long.parseLong(input1[i]);
                } catch (Exception e) {
                    numbers[i] = -1;
                }
            }
        } else {
            numParemeters = 1;
            numbers = new long[1];

            try {
                numbers[0] = Long.parseLong(input1[0]);
            } catch (Exception e) {
                numbers[0] = -1;
            }
        }
    }

    public static boolean isNaturalNumber(long x) {
        return x > 0;
    }

    public static void checkProperties() {
        isEven();
        isOdd();
        isBuzz();
        isDuck();
        isPalindromic();
        isGapful();
    }

    public static void checkProperties(long x) {
        numbers[0] = x;
        isEven();
        isOdd();
        isBuzz();
        isDuck();
        isPalindromic();
        isGapful();
    }

    public static void isEven() {
        even = numbers[0] % 2 == 0;
    }

    public static void isOdd() {
        odd = numbers[0] % 2 == 1;
    }

    public static void isBuzz() {
        buzz = endsWith7() || isDivisibleBy7();
    }

    public static boolean endsWith7() {
        return numbers[0] % 10 == 7;
    }

    public static boolean isDivisibleBy7() {
        return numbers[0] % 7 == 0;
    }

    public static void isDuck() {
        duck = Long.toString(numbers[0]).contains("0");
    }

    public static void isPalindromic() {
        StringBuilder reverseNumber = new StringBuilder();
        String stringNumber = Long.toString(numbers[0]);

        for (int i = stringNumber.length() - 1; i >= 0; i--) {
            reverseNumber.append(stringNumber.charAt(i));
        }

        palindromic = stringNumber.equals(reverseNumber.toString());
    }

    public static void isGapful() {
        gapful = false;
        String n = String.valueOf(numbers[0]);

        if (n.length() >= 3) {
            int a = Character.getNumericValue(n.charAt(0));
            int b = Character.getNumericValue(n.charAt(n.length() - 1));
            long result = a * 10L + b;

            if (numbers[0] % result == 0) {
                gapful = true;
            }
        }
    }

    public static void showProperties() {
        System.out.printf("""
                
                Properties of %d
                \tbuzz: %b
                \tduck: %b
                \tpalindromic: %b
                \tgapful: %b
                \teven: %b
                \todd: %b
                
                """, numbers[0], buzz, duck, palindromic, gapful, even, odd);
    }

    public static void showProperties(long x) {
        String message = "\t" + x + " is";
        if (buzz) {
            message += " buzz,";
        }
        if (duck) {
            message += " duck,";
        }
        if (palindromic) {
            message += " palindromic,";
        }
        if (gapful) {
            message += " gapful,";
        }
        if (even) {
            message += " even,";
        }
        if (odd) {
            message += " odd,";
        }


        System.out.println(message.substring(0, message.length() - 1));
    }
}
