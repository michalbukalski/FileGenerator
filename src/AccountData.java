import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AccountData {
    private List<String> accountNumbers;

    // Konstruktor klasy
    public AccountData(List<String> accountNumbers) {
        this.accountNumbers = accountNumbers;
    }

    // Metoda do losowego wybierania numeru konta
    public String getRandomAccountNumber() {
        Random random = new Random();
        int index = random.nextInt(accountNumbers.size());  // Losowanie indeksu
        return accountNumbers.get(index);  // Zwrócenie numeru konta z listy
    }

    // Metoda do wczytywania numerów kont z pliku konfiguracyjnego
    public static AccountData fromConfigFile() {
        List<String> accountNumbers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("accounts.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                accountNumbers.add(line.trim());  // Dodajemy numer konta do listy
            }
            return new AccountData(accountNumbers);  // Zwracamy obiekt AccountData z listą numerów kont
        } catch (IOException e) {
            System.out.println("Błąd wczytywania numerów kont: " + e.getMessage());
            return null;
        }
    }
}