import java.io.*;
import java.util.*;

public class Lesson5 {
    public static void main(String[] args) {
//        task1();
//        task3();
//        task5();
        task7();
    }

    private static void task1() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter file path: ");
        String filePath = scanner.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error occurred while reading the file: " + e.getMessage());
        }

        scanner.close();
    }

    private static void task3() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter file path: ");
        String filePath = scanner.nextLine();

        System.out.print("Enter word to search for: ");
        String searchWord = scanner.nextLine();

        int wordCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\W+");
                for (String word : words) {
                    if (word.equalsIgnoreCase(searchWord)) {
                        wordCount++;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error occurred while reading the file: " + e.getMessage());
        }

        System.out.println("The word \"" + searchWord + "\" appears " + wordCount + " times in the file.");

        scanner.close();
    }

    private static void task5() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter file path: ");
        String filePath = scanner.nextLine();

        System.out.print("Enter the word to search for: ");
        String searchWord = scanner.nextLine();

        System.out.print("Enter the word to replace with: ");
        String replaceWord = scanner.nextLine();

        int replacementCount = 0;
        StringBuilder fileContent = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String modifiedLine = line.replaceAll("\\b" + searchWord + "\\b", replaceWord);
                replacementCount += countOccurrences(line, searchWord);
                fileContent.append(modifiedLine).append(System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error occurred while reading the file: " + e.getMessage());
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(fileContent.toString());
        } catch (IOException e) {
            System.out.println("Error occurred while writing to the file: " + e.getMessage());
            return;
        }

        System.out.println("The word \"" + searchWord + "\" was replaced " + replacementCount + " times with \"" + replaceWord + "\" in the file.");

        scanner.close();
    }

    private static void task7(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter file path: ");
        String filePath = scanner.nextLine();

        System.out.print("Enter the prohibited words (comma separated): ");
        String prohibitedWordsInput = scanner.nextLine();
        List<String> prohibitedWords = Arrays.asList(prohibitedWordsInput.split("\\s*,\\s*"));

        Map<String, Integer> wordRemovalCount = new HashMap<>();
        StringBuilder fileContent = new StringBuilder();

        for (String word : prohibitedWords) {
            wordRemovalCount.put(word, 0);
        }

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                for (String word : prohibitedWords) {
                    int count = countOccurrences(line, word);
                    wordRemovalCount.put(word, wordRemovalCount.get(word) + count);
                    line = line.replaceAll("\\b" + word + "\\b", "");
                }
                fileContent.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error occurred while reading the file: " + e.getMessage());
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(fileContent.toString());
        } catch (IOException e) {
            System.out.println("Error occurred while writing to the file: " + e.getMessage());
            return;
        }

        System.out.println("Removal Report:");
        for (Map.Entry<String, Integer> entry : wordRemovalCount.entrySet()) {
            System.out.println("The word \"" + entry.getKey() + "\" was removed " + entry.getValue() + " times.");
        }

        scanner.close();
    }

    private static int countOccurrences(String line, String word) {
        int count = 0;
        int idx = 0;
        while ((idx = line.indexOf(word, idx)) != -1) {
            if ((idx == 0 || !Character.isLetterOrDigit(line.charAt(idx - 1))) &&
                    (idx + word.length() == line.length() || !Character.isLetterOrDigit(line.charAt(idx + word.length())))) {
                count++;
            }
            idx += word.length();
        }
        return count;
    }
}
