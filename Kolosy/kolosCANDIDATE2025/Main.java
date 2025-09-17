public class Main {
    public static void main(String[] args) {
        Election election = new Election();//tworzymy obiekt election, będzie nam potrzebny później
        election.populate();
        System.out.println("election .getCandidates() = " + election.getCandidates().size());
        Vote summarize = Vote.summarize(election.firstTurn.votes); // połączenie wszystkich 7000 obwodów w globalne głosowanie
        System.out.println("summarize.toString() = " + summarize);

        System.out.println(election.firstTurn.winner());
    }
}