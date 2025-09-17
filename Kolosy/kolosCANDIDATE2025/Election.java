import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Election {
    private List<Candidate> candidates = new ArrayList<>();

    public ElectionTurn firstTurn; //stworzenie obiektu
    public ElectionTurn secondTurn = null; // to jeszcze nie jest obiekt

    private Candidate winner =new Candidate(null);

    public Candidate getWinner() {
        return winner;
    }

    public ElectionTurn getFirstTurn() {
        return firstTurn;
    }

    public ElectionTurn getSecondTurn() {
        return secondTurn;
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    //metoda zwarcajaca nowa liste bedaca kopia oryginalnej listy candidates,
    // zachowujac te same referencje do obiektow
    public List<Candidate> Election(List<Candidate> candidates) {
        List<Candidate> list = new ArrayList<>(candidates);
        for (int i = 0; i < candidates.size(); i++) {
            list.add(candidates.get(i));
        }
        return list;
    }

    //wczytywanie danych i zapsanie ich do listy candidates
    public void populateCandidates(String path) {
        try {
            Scanner scanner = new Scanner(new File(path));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                candidates.add(new Candidate(line));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //wywolanie metody w metodzie
    public void populate() {
        populateCandidates("src/kandydaci.txt"); // uzupelniamy candidates
        firstTurn = new ElectionTurn(candidates); // tworzymy nowy election turn na podstawie tych kandydatow
        firstTurn.populate("src/1.csv"); // uzupelniamy election turn
        firstTurn.winner();
    }


}
