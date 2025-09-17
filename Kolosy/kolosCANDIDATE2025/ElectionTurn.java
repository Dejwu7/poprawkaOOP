import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ElectionTurn {
    public List<Candidate> candidates;

    public ElectionTurn(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    public List<Vote> votes = new ArrayList<>();

    public void populate(String path) {
        try {
            // d > _ < b
            //NEW FILE
            Scanner scanner = new Scanner(new File(path)); // NEW FILE!!!!!!!!!!!!!
            //NEW KURWA FILE
            //^^^^^^^^^^^^^
            String firstLine = scanner.nextLine(); // skanujemy pierwszą linie bo jest inna od pozostalych
            // i przyda nam się do zdobycia listy candidates
            List<Candidate> candidatesInTurn = new ArrayList<>();
            String[] split = firstLine.split(","); // dzielym po ","
            //mamy do obsłużenia 2 wypadki
            //Gmina,Powiat,Województwo,Robert BIEDROŃ,Krzysztof BOSAK,Andrzej Sebastian DUDA,Szymon Franciszek HOŁOWNIA,Marek JAKUBIAK,Władysław Marcin KOSINIAK-KAMYSZ,Mirosław Mariusz PIOTROWSKI,Paweł Jan TANAJNO,Rafał Kazimierz TRZASKOWSKI,Waldemar Włodzimierz WITKOWSKI,Stanisław Józef ŻÓŁTEK
            //Gmina,Powiat,Województwo,Andrzej Sebastian DUDA,Rafał Kazimierz TRZASKOWSKI
            for (int i = 3; i < split.length; i++) { // pomijamy 3 pierwsze Gmina,Powiat,Województwo i lecimy do końca
                for (Candidate candidate : this.candidates) { // lecimy przez wszystkich kandydatów
                    if (candidate.name().equals(split[i])) { // jeżeli w tej turze jest ten kandydat
                        candidatesInTurn.add(candidate); // dodajemy go do listy kandydatów w tej turze
                        // używamy tego samego obiektu Candidate nie tworzymy nowego!
                    }
                }
            }
            while (scanner.hasNextLine()) { // skanujemy plik
                String line = scanner.nextLine();
                votes.add(Vote.fromCsvLine(candidatesInTurn, line)); // do listy votes dodajemy kolejny element, dodajemy wynik działania statycznej metody fromCsvLine(zwraca obiekt Vote)
            }
            scanner.close();
        } catch (FileNotFoundException e) { // auto-generated
            throw new RuntimeException(e);  // auto-generated
        } // auto-generated
    }

    public Vote getVote(int index) {
        return votes.get(index);
    }

    public int get_size_Votes() {
        return votes.size();
    }

    public Candidate winner() {
        for (Vote vote : votes) {
            for (Candidate candidate : candidates) {
                if (vote.percentage(candidate) > 50) {
                    return candidate;
                }
            }
        }
        throw new NoWinnerException("brak zwycięzcy w pierwszej turze");
    }





}
