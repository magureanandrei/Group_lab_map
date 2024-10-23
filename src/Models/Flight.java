package Models;

public class Flight {
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
}
