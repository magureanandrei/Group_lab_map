package Models;

import java.io.Serializable; /**
 * Represents a custom Pair class for storing two related strings, such as locations.
 */
public class Pair implements HasID{
    private String first;
    private String second;

    /**
     * Constructs a new Pair with the specified values.
     *
     * @param first  The first element of the pair, representing the "from" value.
     * @param second The second element of the pair, representing the "to" value.
     */
    public Pair(String first, String second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Gets the "from" value of the pair.
     *
     * @return The first element of the pair.
     */
    public String getFrom() {
        return first;
    }

    /**
     * Gets the "to" value of the pair.
     *
     * @return The second element of the pair.
     */
    public String getTo() {
        return second;
    }

    /**
     * Sets the "from" value of the pair.
     *
     * @param first The value to set as the first element of the pair.
     */
    public void setFrom(String first) {
        this.first = first;
    }

    /**
     * Sets the "to" value of the pair.
     *
     * @param second The value to set as the second element of the pair.
     */
    public void setTo(String second) {
        this.second = second;
    }

    /**
     * Returns a string representation of the Pair in the format "(first, second)".
     *
     * @return A string representation of the Pair.
     */
    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }

    @Override
    public String[] getHeader() {
        return new String[]{"first", "second"};
    }

    @Override
    public String toCSV() {
        return String.join(",", first, second);
    }


    public static Pair fromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        Pair pair = new Pair(parts[0], parts[1]);
        return pair;
    }


    @Override
    public Integer getID() {
        return 0;
    }
}


