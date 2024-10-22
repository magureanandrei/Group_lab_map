public class Billing {
    public int amount;
    public String Description;
    public Doctor doctor;
    public Patient patient;
    public String date;
    public String method_of_payment;

    public Billing(int amount, String description, Doctor doctor, Patient patient, String date, String method_of_payment) {
        this.amount = amount;
        Description = description;
        this.doctor = doctor;
        this.patient = patient;
        this.date = date;
        this.method_of_payment = method_of_payment;
    }
}
