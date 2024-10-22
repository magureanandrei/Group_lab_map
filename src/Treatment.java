public class Treatment {
    public String name;
    public String duration;
    public Doctor doctor;
    public Patient patient;

    public Treatment(String name, String duration, Doctor doctor, Patient patient) {
        this.name = name;
        this.duration = duration;
        this.doctor = doctor;
        this.patient = patient;
    }
}
