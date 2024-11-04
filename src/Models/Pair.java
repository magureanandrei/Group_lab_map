package Models;
// Custom Pair class
public class Pair {
    private String first;
    private String second;

    public Pair(String first, String second) {
        this.first = first;
        this.second = second;
    }

    public String getFrom() {
        return first;
    }

    public String getTo() {
        return second;
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}


