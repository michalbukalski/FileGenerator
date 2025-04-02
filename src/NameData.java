import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class NameData {
    private List<String> firstNames = Arrays.asList(
            "Jan", "Anna", "Piotr", "Katarzyna", "Tomasz", "Agnieszka", "Michał", "Ewa", "Adam", "Zuzanna",
            "Kamil", "Magdalena", "Maciej", "Julia", "Paweł", "Olga", "Rafał", "Monika", "Marek", "Iwona",
            "Andrzej", "Agata", "Szymon", "Marta", "Wojciech", "Karolina", "Paweł", "Dorota", "Jakub", "Halina"
    );

    private List<String> lastNames = Arrays.asList(
            "Nowak", "Kowalski", "Wiśniewski", "Wójcik", "Kowalczyk", "Kamiński", "Lewandowski", "Zieliński",
            "Szymański", "Dąbrowski", "Sikora", "Jankowski", "Wójcik", "Mazur", "Woźniak", "Piotrowski", "Kaczmarek",
            "Piątek", "Michalski", "Sikorski", "Kruk", "Czerwiński", "Adamczak", "Zawisza", "Mikolajczyk", "Rogalski",
            "Górski", "Sadowski", "Kubiak", "Kwiatkowski"
    );

    private Random random = new Random();

    // Metoda do losowania imienia
    public String getRandomFirstName() {
        return firstNames.get(random.nextInt(firstNames.size()));
    }

    // Metoda do losowania nazwiska
    public String getRandomLastName() {
        return lastNames.get(random.nextInt(lastNames.size()));
    }

    // Metoda do ładowania danych z pliku konfiguracyjnego (przykład)
    public static NameData fromConfigFile() {
        // Tutaj można dodać logikę wczytywania imion i nazwisk z pliku konfiguracyjnego,
        // jeśli chcesz mieć większą elastyczność. Na razie używamy statycznych danych.
        return new NameData();
    }
}
