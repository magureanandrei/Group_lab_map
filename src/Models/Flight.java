package Models;

public class Flight implements HasID{
    public int flightID;
    public String from;
    public String to;
    public Pilot pilot;

    public Flight(int flightID, String from, String to, Pilot pilot) {
        this.flightID = flightID;
        this.from = from;
        this.to = to;
        this.pilot = pilot;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightID=" + flightID +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", pilot=" + pilot +
                '}';
    }

    @Override
    public int getID() {
        return flightID;
    }
}
