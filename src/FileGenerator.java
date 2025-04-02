import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.Scanner;

public class FileGenerator {
    private static String NUMBER;
    private static NameData nameData;
    private static CommentGenerator commentGenerator;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Wczytanie danych z pliku konfiguracyjnego
        String sizeInput = loadConfig();
        if (sizeInput == null || nameData == null || commentGenerator == null) {
            System.out.println("Błąd wczytywania konfiguracji.");
            return;
        }

        // Zapytanie użytkownika o format pliku
        System.out.println("Wybierz format pliku, który chcesz wygenerować:");
        System.out.println("1. CSV");
        System.out.println("2. TXT");
        System.out.println("3. XML");
        int fileFormatChoice = scanner.nextInt();

        // Zapytanie użytkownika, czy chce generować plik na podstawie rozmiaru czy liczby wierszy
        System.out.println("Wybierz opcję:");
        System.out.println("1. Generowanie pliku na podstawie rozmiaru (MB/KB)");
        System.out.println("2. Generowanie pliku na podstawie liczby wierszy");

        int choice = scanner.nextInt();
        long fileSize = 0;
        int numRows = 0;

        if (choice == 1) {
            // Zapytanie użytkownika o rozmiar pliku i jednostkę
            System.out.println("Podaj rozmiar pliku:");
            System.out.println("1. W MB");
            System.out.println("2. W KB");
            int sizeChoice = scanner.nextInt();

            if (sizeChoice == 1) {
                System.out.println("Podaj rozmiar w MB (np. 10):");
                double sizeMB = scanner.nextDouble();
                fileSize = (long) (sizeMB * 1024 * 1024);  // Przeliczamy na bajty
            } else if (sizeChoice == 2) {
                System.out.println("Podaj rozmiar w KB (np. 500):");
                double sizeKB = scanner.nextDouble();
                fileSize = (long) (sizeKB * 1024);  // Przeliczamy na bajty
            } else {
                System.out.println("Nieprawidłowy wybór jednostki.");
                return;
            }

            if (fileSize <= 0) {
                System.out.println("Nieprawidłowy rozmiar pliku.");
                return;
            }
        } else if (choice == 2) {
            // Zapytanie użytkownika o liczbę wierszy
            System.out.println("Podaj liczbę wierszy, które mają być wygenerowane:");
            numRows = scanner.nextInt();
        } else {
            System.out.println("Nieprawidłowy wybór.");
            return;
        }

        // Określenie ścieżki na podstawie wyboru formatu
        String filePath = "C:\\Users\\Michal\\Downloads\\output.csv";  // Domyślnie ustawiamy jako CSV
        if (fileFormatChoice == 2) {
            filePath = "C:\\Users\\Michal\\Downloads\\output.txt";  // Wybór TXT
        } else if (fileFormatChoice == 3) {
            filePath = "C:\\Users\\Michal\\Downloads\\output.xml";  // Wybór XML
        }

        // Generowanie pliku na podstawie wybranej opcji
        generateFile(filePath, fileSize, numRows, fileFormatChoice);
        System.out.println("Plik został wygenerowany w folderze Downloads.");
    }

    private static String loadConfig() {
        try (BufferedReader reader = new BufferedReader(new FileReader("config.txt"))) {
            String line;
            String size = null;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("size=")) {
                    size = line.split("=")[1].trim();
                } else if (line.startsWith("number=")) {
                    NUMBER = line.split("=")[1].trim();
                }
            }

            nameData = NameData.fromConfigFile();
            commentGenerator = new CommentGenerator();  // Inicjalizujemy generowanie komentarzy
            return size;
        } catch (IOException e) {
            System.out.println("Błąd wczytywania pliku konfiguracyjnego: " + e.getMessage());
            return null;
        }
    }

    private static void generateFile(String fileName, long fileSize, int numRows, int fileFormatChoice) {
        Random random = new Random();

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), StandardCharsets.UTF_8))) {
            long currentSize = 0;
            int rowCount = 0;

            while ((fileSize > 0 && currentSize < fileSize) || (numRows > 0 && rowCount < numRows)) {
                String firstName = nameData.getRandomFirstName();
                String lastName = nameData.getRandomLastName();
                StringBuilder comments = new StringBuilder();

                // Generowanie 50 komentarzy (jeśli to plik CSV)
                if (fileFormatChoice == 1) {
                    for (int i = 1; i <= 50; i++) {
                        String comment = commentGenerator.generateRandomComment();
                        comments.append("komentarz").append(i).append(": ").append(comment).append("; ");
                    }
                }

                String line = firstName + " " + lastName + ";" + NUMBER + ";" + firstName + " " + lastName + ";" + comments.toString().trim() + "\n";
                writer.write(line);
                currentSize += line.getBytes(StandardCharsets.UTF_8).length;
                rowCount++;

                // Jeśli generujemy plik na podstawie wierszy, zatrzymujemy się po osiągnięciu wymaganej liczby wierszy
                if (numRows > 0 && rowCount >= numRows) {
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Błąd zapisu pliku: " + e.getMessage());
        }
    }
}
