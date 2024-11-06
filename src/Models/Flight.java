package Models;

public class Flight implements HasID{
    public Integer flightID;
    public String from;
    public String to;
    public Pilot pilot;
    public Airplane airplane;

    public Flight(Integer flightID, String from, String to, Pilot pilot,Airplane airplane) {
        this.flightID = flightID;
        this.from = from;
        this.to = to;
        this.pilot = pilot;
        this.airplane = airplane;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightID=" + flightID +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", pilot=" + pilot +
                ", airplane=" + airplane +
                '}';
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Pilot getPilot() {
        return pilot;
    }

    public void setPilot(Pilot pilot) {
        this.pilot = pilot;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    @Override
    public Integer getID() {
        return flightID;
    }
}
