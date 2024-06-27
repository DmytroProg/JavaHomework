import java.util.Random;
import java.util.Scanner;

public class Lesson1 {
    public static void main(String[] args) {
//        task1();
//        task2();
//        task3();
//        task4();
        task5();
    }

    private static void task5() {
        int[] array = new int[100];
        Random random = new Random();

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(101) - 50;
        }

        int min = array[0];
        int max = array[0];
        int negativeCount = 0;
        int positiveCount = 0;
        int zeroCount = 0;

        for (int value : array) {
            if (value < min) {
                min = value;
            }
            if (value > max) {
                max = value;
            }
            if (value < 0) {
                negativeCount++;
            } else if (value > 0) {
                positiveCount++;
            }
        }

        zeroCount = array.length - negativeCount - positiveCount;

        System.out.println("Min value: " + min);
        System.out.println("Max value: " + max);
        System.out.println("Count of negative: " + negativeCount);
        System.out.println("Count of positive: " + positiveCount);
        System.out.println("Count of zeros: " + zeroCount);
    }

    private static void task4() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter first number: ");
        int num1 = scanner.nextInt();

        System.out.print("Enter second number: ");
        int num2 = scanner.nextInt();

        for(int i = num1; i <= num2; i++) {
            for(int j = 1; j <= 10; j++){
                System.out.print(i + "*" + j + "=" + i*j + "; ");
            }
            System.out.println();
        }

        scanner.close();
    }

    private static void task3() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter order number of a month (1-12): ");
        int month = scanner.nextInt();

        String season = switch (month) {
            case 12, 1, 2 -> "Winter";
            case 3, 4, 5 -> "Spring";
            case 6, 7, 8 -> "Summer";
            case 9, 10, 11 -> "Autumn";
            default -> "Number must be in range of 1 and 12";
        };

        System.out.println(season);

        scanner.close();
    }

    private static void task2() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter 6-digit number: ");
        String input = scanner.nextLine();

        if (input.length() != 6) {
            System.out.println("Number must have 6 digits");
            return;
        }
        char[] digits = input.toCharArray();
        char temp;

        temp = digits[0];
        digits[0] = digits[5];
        digits[5] = temp;

        temp = digits[1];
        digits[1] = digits[4];
        digits[4] = temp;

        String swappedNumber = new String(digits);
        System.out.println("Result: " + swappedNumber);

        scanner.close();
    }

    public static void task1() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number: ");
        double value = scanner.nextDouble();

        System.out.print("Enter percent: ");
        double percentage = scanner.nextDouble();

        double result = value * (percentage / 100.0);

        System.out.println(percentage + "% of " + value + " = " + result);

        scanner.close();
    }
}
