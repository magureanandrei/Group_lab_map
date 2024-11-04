import Models.*;
import Repo.Repository;

import java.util.ArrayList;
import java.util.List;

public class Service {

    private final Repository<Pilot> pilotsRepo;
    private final Repository<Passenger> passengerRepo;
    private final Repository<CabinCrew> cabinCrewRepo;
    private final Repository<Flight> flightRepo;



    public Service(Repository<Pilot> pilotsRepo, Repository<Passenger> passengerRepo, Repository<CabinCrew> cabinCrewRepo, Repository<Flight> flightRepo) {
        this.pilotsRepo = pilotsRepo;
        this.passengerRepo = passengerRepo;
        this.cabinCrewRepo = cabinCrewRepo;
        this.flightRepo = flightRepo;
    }

    public List<Pilot> getPilots() {return pilotsRepo.getAll();}

    public List<Passenger> getAllPassengers() {return passengerRepo.getAll();}

    public List<Passenger> getPassengersByFlight(Flight flightPara) {
        ArrayList<Passenger> passengerByFlight = new ArrayList<Passenger>();
        for( Passenger passenger: passengerRepo.getAll())
            for(Pair pair: passenger.flight)
            {
                if(pair.getFrom().equals(flightPara.from) && pair.getTo().equals(flightPara.to))
                    passengerByFlight.add(passenger);
            }
        return passengerByFlight;
    }

    public List<CabinCrew> getAllCabinCrews() {return cabinCrewRepo.getAll();}

}
