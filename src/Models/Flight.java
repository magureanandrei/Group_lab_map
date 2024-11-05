package Models;

public class Flight implements HasID{
    public Integer flightID;
    public String from;
    public String to;
    public Pilot pilot;

    public Flight(Integer flightID, String from, String to, Pilot pilot) {
        this.flightID = flightID;
        this.from = from;
        this.to = to;
        this.pilot = pilot;
    }

    @Override
    public Integer getID() {
        return flightID;
    }

}
