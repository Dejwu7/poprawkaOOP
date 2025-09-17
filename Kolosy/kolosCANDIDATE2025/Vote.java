import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Vote {
    private Map<Candidate, Integer> votesForCandidate = new HashMap<>();
    private List<String> location = new ArrayList<>();


    public static Vote fromCsvLine(List<Candidate> candidates, String line) {
        // 2 przypadki
        //m. Bolesławiec,bolesławiecki,dolnośląskie,450,1131,6465,2899,32,255,13,19,7183,24,43
        //m. Bolesławiec,bolesławiecki,dolnośląskie,7915,11069
        // jebie nas bolesławiec
        Map<Candidate, Integer> map = new HashMap<>(); // tworzymy nową mapę w której zachowamy sumę głosów na kandydatów
        String[] split = line.split(",");
        for (int i = 3; i < split.length; i++) { // znowu zaczynamy od 3 bo nas jebie m. Bolesławiec,bolesławiecki,dolnośląskie
            Candidate candidate = candidates.get(i - 3); // odejmujemy 3 bo nasz index jest przesunięty o Bolesławiec
            int sum = map.getOrDefault(candidate, 0); // pobieramy obecną sumę głosów na kandydata lub 0
            sum += Integer.parseInt(split[i]); // tu używamy normalneog indexu bo w split jest Bolesławiec
            map.put(candidate, sum); // zapisujemy sume w mapie
        }
        List<String> location = new ArrayList<>(); // tworzyym nową listę do lokalizacji
        location.add(split[2]); // wojewodztwo
        location.add(split[1]); // gmina
        location.add(split[0]); // miasto
        Vote vote = new Vote(); // tworzymy nowy obiekt
        vote.votesForCandidate = map; // ustawiamy mape glosow
        vote.location = location; // ustawiamy lokalizacje
        return vote;
    }
    public void setVote(Vote vote)
    {
        this.votesForCandidate=vote.votesForCandidate;  //setter dla mapy gdyz jest prytwana
        this.location=vote.location; //setter dla listy gdyz jest prytwana
    }

    public static Vote summarize(List<Vote> votes) {
        Map<Candidate, Integer> sumMap = new HashMap<>(); // tworzymy nową mapę z sumą
        // kandydat -> suma głosów
        for (Vote vote1 : votes) {// przechodzimy przez wszystkie głosowania
            for (Candidate candidate : vote1.votesForCandidate.keySet()) { // przechodzimy przez każdego kandydata w tym głosowaniu
                int sum = sumMap.getOrDefault(candidate, 0); // pobieramy ile kandydat uzbierał do tej pory głosów lub 0
                sum += vote1.votesForCandidate.get(candidate); // dodajemy głosy zdobyte w tym konkretnym głosowaniu
                sumMap.put(candidate, sum); // zmieniamy w mapie
            }
        }

        Vote vote = new Vote(); // tworzymy nowy obiekt Vote
        vote.votesForCandidate = sumMap; // ustawiamy mu mapę na naszą sumę
        vote.location = null; //ustawianie jako pusta liste
        return vote;
    }

    public int votes(Candidate candidate) {
        return votesForCandidate.get(candidate); //zwracanie liczby glosow na poszczegolnego kandydata
    }

    public double percentage(Candidate candidate) {
        int votes = this.votesForCandidate.getOrDefault(candidate, 0); // pobieramy liczbę głosów lub 0
        int sum = 0;
        // do obliczenia %, potrzebujemy obliczyć liczbę wszystkich głosów
        for (Integer v : this.votesForCandidate.values()) { // w pętli przechodzimy tylko po sumie głosów
            sum += v; // dodajemy do sumę liczbę głosów każdego z kandydatów
        }
        return (double) votes / sum * 100D; //rzutujemy liczbę na double aby nie mieć zaokrąglania i mnożymy * 100 aby mieć %
    }

    @Override
    public String toString() {
        StringBuilder mapAsString = new StringBuilder(); // nowy stringbuilder do łatwego sklejania tekstu
        for (Candidate candidate : votesForCandidate.keySet()) { // przechodzimy przez wszystkich kandydatów
            mapAsString.append(candidate.name() + " = " + percentage(candidate) + "%\n"); // do stringbuildera dodajemy NAZWE kandydata i jego % otrzymanych głosów a na końcu "\n" nowa linia
        }
        mapAsString.delete(mapAsString.length() - 2, mapAsString.length()); // usuwamy 2 ostatnie znaki (\n), aby uniknąć zbędnego symbolu nowej linii
        return mapAsString.toString();
    }
}
