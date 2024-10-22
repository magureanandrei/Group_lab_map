import java.util.List;

public class Pharmacy {
    public List<String> Meds_names;
    public List<Integer> Meds_inventory;
    public String Location;

    public Pharmacy(List<String> meds_names, List<Integer> meds_inventory, String location) {
        Meds_names = meds_names;
        Meds_inventory = meds_inventory;
        Location = location;
    }
}
