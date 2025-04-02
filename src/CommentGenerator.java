import java.util.List;
import java.util.Random;

public class CommentGenerator {
    private static final List<String> words = List.of(
            "losowy", "tekst", "dla", "komentarza", "przykładowego", "generowanego",
            "w", "programie", "Java", "testowego", "słowa", "w", "komentarzach", "opinia",
            "opis", "wypowiedź", "recenzja", "refleksja", "opinia", "zdanie",
            "analiza", "komentarz", "pogląd", "spostrzeżenie", "ocena", "przemówienie"
            // Dodaj więcej słów do listy, jeśli potrzebujesz
    );

    private Random random = new Random();

    // Metoda do generowania losowego komentarza o długości 50 słów
    public String generateRandomComment() {
        int commentLength = 50;  // Stała długość komentarza (50 słów)
        StringBuilder comment = new StringBuilder();

        for (int i = 0; i < commentLength; i++) {
            // Losujemy słowo z listy i dodajemy do komentarza
            comment.append(words.get(random.nextInt(words.size()))).append(" ");
        }

        return comment.toString().trim();  // Zwracamy wygenerowany komentarz
    }

    // Metoda do generowania listy 50 komentarzy
    public String[] generateComments(int numberOfComments) {
        String[] comments = new String[numberOfComments];
        for (int i = 0; i < numberOfComments; i++) {
            comments[i] = generateRandomComment();
        }
        return comments;
    }
}
