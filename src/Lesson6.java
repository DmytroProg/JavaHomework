import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Lesson6 {
    private static int max = Integer.MIN_VALUE;
    private static int min = Integer.MAX_VALUE;
    private static int sum = 0;
    private static double average = 0.0;
    private static int evenCount = 0;
    private static int oddCount = 0;
    private static AtomicInteger occurrences = new AtomicInteger(0);
    private static final Object locker = new Object();

    public static void main(String[] args) {
//        task1();
//        task2();
//        task3();
        task4();
    }

    private static void task4() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter the path to the file: ");
        String filePath;
        try {
            filePath = reader.readLine();
        } catch (IOException e) {
            System.out.println("Error reading input.");
            return;
        }

        System.out.print("Enter the word to search for: ");
        String wordToSearch;
        try {
            wordToSearch = reader.readLine();
        } catch (IOException e) {
            System.out.println("Error reading input.");
            return;
        }

        Runnable searchWord = () -> {
            try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = fileReader.readLine()) != null) {
                    String[] words = line.split("\\s+");
                    for (String word : words) {
                        if (word.equalsIgnoreCase(wordToSearch)) {
                            occurrences.incrementAndGet();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        Thread searchThread = new Thread(searchWord);

        searchThread.start();

        try {
            searchThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Occurrences of '" + wordToSearch + "' in the file: " + occurrences.get());

        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void task3() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter the path to the file containing numbers: ");
        String filePath;
        try {
            filePath = reader.readLine();
        } catch (IOException e) {
            System.out.println("Error reading input.");
            return;
        }

        List<Integer> numbers = readNumbersFromFile(filePath);

        Runnable writeEvenNumbers = () -> {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("even_numbers.txt"));
                for (int num : numbers) {
                    if (num % 2 == 0) {
                        writer.write(num + "\n");
                        evenCount++;
                    }
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        Runnable writeOddNumbers = () -> {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("odd_numbers.txt"));
                for (int num : numbers) {
                    if (num % 2 != 0) {
                        writer.write(num + "\n");
                        oddCount++;
                    }
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        Thread evenThread = new Thread(writeEvenNumbers);
        Thread oddThread = new Thread(writeOddNumbers);

        evenThread.start();
        oddThread.start();

        try {
            evenThread.join();
            oddThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Number of even numbers: " + evenCount);
        System.out.println("Number of odd numbers: " + oddCount);

        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Integer> readNumbersFromFile(String filePath) {
        List<Integer> numbers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    int number = Integer.parseInt(line.trim());
                    numbers.add(number);
                } catch (NumberFormatException e) {
                    System.out.println("Skipping non-integer value: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return numbers;
    }

    private static void task2() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of elements in the array: ");
        int n = scanner.nextInt();
        int[] array = new int[n];

        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }

        Runnable calculateSum = () -> {
            int localSum = 0;
            for (int num : array) {
                localSum += num;
            }
            synchronized (locker) {
                sum = localSum;
            }
        };

        Runnable calculateAverage = () -> {
            int localSum = 0;
            for (int num : array) {
                localSum += num;
            }
            synchronized (locker) {
                average = (double) localSum / array.length;
            }
        };

        Thread sumThread = new Thread(calculateSum);
        Thread averageThread = new Thread(calculateAverage);

        sumThread.start();
        averageThread.start();

        try {
            sumThread.join();
            averageThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Sum of the array elements: " + sum);
        System.out.println("Average of the array elements: " + average);

        scanner.close();
    }

    private static void task1() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of elements in the array: ");
        int n = scanner.nextInt();
        int[] array = new int[n];

        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }

        Runnable findMax = () -> {
            for (int num : array) {
                synchronized (locker) {
                    if (num > max) {
                        max = num;
                    }
                }
            }
        };

        Runnable findMin = () -> {
            for (int num : array) {
                synchronized (locker) {
                    if (num < min) {
                        min = num;
                    }
                }
            }
        };

        Thread maxThread = new Thread(findMax);
        Thread minThread = new Thread(findMin);

        maxThread.start();
        minThread.start();

        try {
            maxThread.join();
            minThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Maximum value in the array: " + max);
        System.out.println("Minimum value in the array: " + min);

        scanner.close();
    }
}
