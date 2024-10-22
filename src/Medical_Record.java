import java.util.List;

public class Medical_Record {
    public String date; // ar putea fi lista si sa fie in concordanta cu lista diagnostics
    public Patient patient;
    public List<String> diagnostics;
    public String treatment; //si aici poate fi lista

    public Medical_Record(String date, Patient patient, List<String> diagnostics, String treatment) {
        this.date = date;
        this.patient = patient;
        this.diagnostics = diagnostics;
        this.treatment = treatment;
    }
}
