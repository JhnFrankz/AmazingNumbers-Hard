package numbers;

import java.util.Scanner;

/*enum Properties {
    BUZZ,
    DUCK,
    PALINDROMIC,
    GAPFUL,
    SPY,
    EVEN,
    ODD
}*/

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static long number = -1;
    private static long amount = -1;
    private static String property = "-1";
    private static boolean even;
    private static boolean odd;
    private static boolean buzz;
    private static boolean duck;
    private static boolean palindromic;
    private static boolean gapful;
    private static boolean spy;
    private static int parameters = 0;

    public static void main(String[] args) {
        showWelcome();
        start();
    }

    public static void start() {
        readLine();

        while (number != 0) {
            switch (parameters) {
                case 1:
                    if (isNaturalNumber(number)) {
                        checkProperties();
                        showProperties();
                    } else {
                        System.out.println("The first parameter should be a natural number or zero.");
                    }
                    break;
                case 2:
                    if (isNaturalNumber(number) && isNaturalNumber(amount)) {
                        long initialNumber = number;

                        for (long i = number; i < initialNumber + amount; i++) {
                            checkProperties(i);
                            showProperties(i);
                        }
                    } else if (!isNaturalNumber(number)) {
                        System.out.println("The first parameter should be a natural number or zero.");
                    } else if (!isNaturalNumber(amount)) {
                        System.out.println("The second parameter should be a natural number.");
                    }
                    break;
                case 3:
                    if (isNaturalNumber(number) && isNaturalNumber(amount) && isValidProperty()) {
                        long counter = 0;

                        for (long i = number; counter < amount; i++) {
                            checkProperties(i);

                            if (verifyProperty(property)) {
                                showProperties(i);
                                counter++;
                            }
                        }
                    } else if (!isNaturalNumber(number)) {
                        System.out.println("The first parameter should be a natural number or zero.");
                    } else if (!isNaturalNumber(amount)) {
                        System.out.println("The second parameter should be a natural number.");
                    } else if (!isValidProperty()) {
                        System.out.printf("""
                        The property [%s] is wrong.
                        Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, EVEN, ODD]
                        """, property.toUpperCase());
                    }
                    break;
                default:
                    break;
            }

            readLine();
        }

        System.out.println("""
                                    
                Goodbye!""");
    }

    public static void showWelcome() {
        System.out.println("""
                Welcome to Amazing Numbers!

                Supported requests:
                - enter a natural number to know its properties;
                - enter two natural numbers to obtain the properties of the list:
                  * the first parameter represents a starting number;
                  * the second parameters show how many consecutive numbers are to be processed;
                - two natural numbers and a property to search for;
                - enter 0 to exit.
                """);
    }

    public static void readLine() {
        System.out.print("Enter a request: ");
        String[] input1 = scanner.nextLine().split(" ");

        if (input1.length == 1) {
            try {
                number = Long.parseLong(input1[0]);
            } catch (Exception e) {
                number = -1;
            }

            parameters = 1;
        } else if (input1.length == 2) {
            try {
                number = Long.parseLong(input1[0]);
            } catch (Exception e) {
                number = -1;
            }

            try {
                amount = Long.parseLong(input1[1]);
            } catch (Exception e) {
                amount = -1;
            }

            parameters = 2;
        } else if (input1.length == 3) {
            try {
                number = Long.parseLong(input1[0]);
            } catch (Exception e) {
                number = -1;
            }

            try {
                amount = Long.parseLong(input1[1]);
            } catch (Exception e) {
                amount = -1;
            }

            property = input1[2];
            parameters = 3;
        }
    }

    public static boolean isNaturalNumber(long x) {
        return x > 0;
    }

    public static boolean isValidProperty() {
        String[] properties = new String[]{"BUZZ", "DUCK", "PALINDROMIC",
                "GAPFUL", "SPY", "EVEN", "ODD"};

        for (String s : properties) {
            if (s.equalsIgnoreCase(property)) {
                return true;
            }
        }

        return false;
    }

    public static boolean verifyProperty(String property) {
        return switch (property.toUpperCase()) {
            case "BUZZ" -> buzz;
            case "DUCK" -> duck;
            case "PALINDROMIC" -> palindromic;
            case "GAPFUL" -> gapful;
            case "SPY" -> spy;
            case "EVEN" -> even;
            case "ODD" -> odd;
            default -> false;
        };
    }

    public static void checkProperties() {
        isEven();
        isOdd();
        isBuzz();
        isDuck();
        isPalindromic();
        isGapful();
        isSpy();
    }

    public static void checkProperties(long x) {
        number = x;
        isEven();
        isOdd();
        isBuzz();
        isDuck();
        isPalindromic();
        isGapful();
        isSpy();
    }

    public static void isEven() {
        even = number % 2 == 0;
    }

    public static void isOdd() {
        odd = number % 2 == 1;
    }

    public static void isBuzz() {
        buzz = endsWith7() || isDivisibleBy7();
    }

    public static boolean endsWith7() {
        return number % 10 == 7;
    }

    public static boolean isDivisibleBy7() {
        return number % 7 == 0;
    }

    public static void isDuck() {
        duck = Long.toString(number).contains("0");
    }

    public static void isPalindromic() {
        StringBuilder reverseNumber = new StringBuilder();
        String stringNumber = Long.toString(number);

        for (int i = stringNumber.length() - 1; i >= 0; i--) {
            reverseNumber.append(stringNumber.charAt(i));
        }

        palindromic = stringNumber.equals(reverseNumber.toString());
    }

    public static void isGapful() {
        gapful = false;
        String n = String.valueOf(number);

        if (n.length() >= 3) {
            int a = Character.getNumericValue(n.charAt(0));
            int b = Character.getNumericValue(n.charAt(n.length() - 1));
            long result = a * 10L + b;

            if (number % result == 0) {
                gapful = true;
            }
        }
    }

    public static void isSpy() {
        spy = false;
        StringBuilder n = new StringBuilder();
        n.append(number);

        long acumSum = 0;
        long acumMul = 1;

        for (int i = 0; i < n.length(); i++) {
            int a = Character.getNumericValue(n.charAt(i));
            acumSum += a;
            acumMul *= a;
        }

        if (acumSum == acumMul) {
            spy = true;
        }
    }

    public static void showProperties() {
        System.out.printf("""
                                
                Properties of %d
                \tbuzz: %b
                \tduck: %b
                \tpalindromic: %b
                \tgapful: %b
                \tspy: %b
                \teven: %b
                \todd: %b
                                
                """, number, buzz, duck, palindromic, gapful, spy, even, odd);
    }

    public static void showProperties(long x) {
        String message = "\t" + x + " is";

        if (spy) {
            message += " spy,";
        }
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
