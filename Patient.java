/**
 * Patient.java
 *
 * Represents a single patient record stored in the Patient BST.
 * Each patient also owns a VisitHistory (Singly Linked List) that
 * keeps track of all their previous hospital visits.
 */
public class Patient {
    private int patientId;
    private String name;
    private int age;
    private String contactNumber;
    private String medicalCondition;

    // Every patient carries their own visit history (Singly Linked List)
    private VisitHistory visitHistory;

    public Patient(int patientId, String name, int age, String contactNumber, String medicalCondition) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.contactNumber = contactNumber;
        this.medicalCondition = medicalCondition;
        this.visitHistory = new VisitHistory();
    }

    public int getPatientId() {
        return patientId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getMedicalCondition() {
        return medicalCondition;
    }

    public void setMedicalCondition(String medicalCondition) {
        this.medicalCondition = medicalCondition;
    }

    public VisitHistory getVisitHistory() {
        return visitHistory;
    }

    @Override
    public String toString() {
        return "Patient ID   : " + patientId +
                "\nName         : " + name +
                "\nAge          : " + age +
                "\nContact      : " + contactNumber +
                "\nCondition    : " + medicalCondition;
    }
}
