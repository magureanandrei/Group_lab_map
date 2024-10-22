public class Appointment {
    public int id;
    public String date;
    public Doctor doctor;
    public Patient patient;

    public Appointment(int id, String date, Doctor doctor, Patient patient) {
        this.id = id;
        this.date = date;
        this.doctor = doctor;
        this.patient = patient;
    }
}
