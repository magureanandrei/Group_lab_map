import java.util.LinkedList;
import java.util.List;

public class Airport {
    String location;
    Integer number_of_airstrips;
    List<Integer> length_of_airstrips;

    public Airport(String location, Integer number_of_airstrips, List<Integer> length_of_airstrips) {
        this.location = location;
        this.number_of_airstrips = number_of_airstrips;
        this.length_of_airstrips = length_of_airstrips;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getNumber_of_airstrips() {
        return number_of_airstrips;
    }

    public void setNumber_of_airstrips(Integer number_of_airstrips) {
        this.number_of_airstrips = number_of_airstrips;
    }

    public List<Integer> getLength_of_airstrips() {
        return length_of_airstrips;
    }

    public void setLength_of_airstrips(List<Integer> length_of_airstrips) {
        this.length_of_airstrips = length_of_airstrips;
    }
}
