import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class Lesson4 {
    public static void main(String[] args) {
//        task1();
//        task2();
//        task3();
        task4();
    }

    private static void task1() {
        Predicate<Integer> isLeapYear = year -> Year.isLeap(year);

        BiFunction<LocalDate, LocalDate, Long> daysBetween = (startDate, endDate) -> ChronoUnit.DAYS.between(startDate, endDate);

        BiFunction<LocalDate, LocalDate, Long> fullSundaysBetween = (startDate, endDate) -> {
            long count = 0;
            LocalDate current = startDate;
            while (!current.isAfter(endDate)) {
                if (current.getDayOfWeek() == DayOfWeek.SUNDAY) {
                    count++;
                }
                current = current.plusWeeks(1);
            }
            return count;
        };

        Function<LocalDate, DayOfWeek> dayOfWeek = date -> date.getDayOfWeek();

        int testYear = 2024;
        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 31);
        LocalDate testDate = LocalDate.of(1969, 7, 20);

        System.out.println("Is " + testYear + " a leap year? " + isLeapYear.test(testYear));
        System.out.println("Days between " + startDate + " and " + endDate + ": " + daysBetween.apply(startDate, endDate));
        System.out.println("Full Sundays between " + startDate + " and " + endDate + ": " + fullSundaysBetween.apply(startDate, endDate));
        System.out.println("Day of the week for " + testDate + ": " + dayOfWeek.apply(testDate));
    }

    private static void task2() {
        BiFunction<int[], int[], int[]> sumFractions = (frac1, frac2) -> {
            int numerator = frac1[0] * frac2[1] + frac2[0] * frac1[1];
            int denominator = frac1[1] * frac2[1];
            return simplifyFraction(numerator, denominator);
        };

        BiFunction<int[], int[], int[]> subtractFractions = (frac1, frac2) -> {
            int numerator = frac1[0] * frac2[1] - frac2[0] * frac1[1];
            int denominator = frac1[1] * frac2[1];
            return simplifyFraction(numerator, denominator);
        };

        BiFunction<int[], int[], int[]> multiplyFractions = (frac1, frac2) -> {
            int numerator = frac1[0] * frac2[0];
            int denominator = frac1[1] * frac2[1];
            return simplifyFraction(numerator, denominator);
        };

        BiFunction<int[], int[], int[]> divideFractions = (frac1, frac2) -> {
            int numerator = frac1[0] / frac2[0];
            int denominator = frac1[1] / frac2[1];
            return simplifyFraction(numerator, denominator);
        };

        int[] frac1 = {1, 2};
        int[] frac2 = {2, 3};

        int[] sumResult = sumFractions.apply(frac1, frac2);
        int[] subtractResult = subtractFractions.apply(frac1, frac2);
        int[] multiplyResult = multiplyFractions.apply(frac1, frac2);
        int[] divideResult = divideFractions.apply(frac1, frac2);

        System.out.println("Sum of fractions: " + sumResult[0] + "/" + sumResult[1]);
        System.out.println("Difference of fractions: " + subtractResult[0] + "/" + subtractResult[1]);
        System.out.println("Product of fractions: " + multiplyResult[0] + "/" + multiplyResult[1]);
        System.out.println("Division of fractions: " + divideResult[0] + "/" + divideResult[1]);
    }

    private static int[] simplifyFraction(int numerator, int denominator) {
        int gcd = gcd(numerator, denominator);
        return new int[]{numerator / gcd, denominator / gcd};
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return Math.abs(a);
    }

    private static void task3(){
        Function<int[], Integer> maxOfFour = numbers -> {
            int max = numbers[0];
            for (int i = 1; i < numbers.length; i++) {
                if (numbers[i] > max) {
                    max = numbers[i];
                }
            }
            return max;
        };

        Function<int[], Integer> minOfFour = numbers -> {
            int min = numbers[0];
            for (int i = 1; i < numbers.length; i++) {
                if (numbers[i] < min) {
                    min = numbers[i];
                }
            }
            return min;
        };

        int[] numbers = {5, 2, 9, 1};

        int maxResult = maxOfFour.apply(numbers);
        int minResult = minOfFour.apply(numbers);

        System.out.println("Maximum of the four numbers: " + maxResult);
        System.out.println("Minimum of the four numbers: " + minResult);
    }

    private static void task4(){
        int[] numbers = {1, -2, 3, 4, -5, 6, -7, 8};

        Predicate<Integer> isEqualTo = num -> num == 3;
        Predicate<Integer> notInRange = num -> num < 2 || num > 6;
        Predicate<Integer> isPositive = num -> num > 0;
        Predicate<Integer> isNegative = num -> num < 0;

        System.out.println("Sum of elements equal to 3: " + sumIf(numbers, isEqualTo));
        System.out.println("Sum of elements not in range 2 to 6: " + sumIf(numbers, notInRange));
        System.out.println("Sum of positive elements: " + sumIf(numbers, isPositive));
        System.out.println("Sum of negative elements: " + sumIf(numbers, isNegative));
    }

    public static int sumIf(int[] array, Predicate<Integer> condition) {
        int sum = 0;
        for (int num : array) {
            if (condition.test(num)) {
                sum += num;
            }
        }
        return sum;
    }
}
