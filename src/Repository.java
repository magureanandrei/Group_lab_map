import java.util.List;

public interface Repository {

    List<Passenger> getPassengers();

    List<Airplane> getAirplanes();

    List<Pilot> getPilot();

    List<Cabin_Crew> getCabinCrew();

    List<Reservation> getReservation();
}