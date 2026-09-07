/**
 * TreatmentRecord.java
 *
 * Represents a completed treatment record, pushed onto the
 * Treatment History Stack once a patient's treatment is finished.
 */
public class TreatmentRecord {
    private int patientId;
    private String patientName;
    private String treatmentDetails;
    private String completionDate;

    public TreatmentRecord(int patientId, String patientName, String treatmentDetails, String completionDate) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.treatmentDetails = treatmentDetails;
        this.completionDate = completionDate;
    }

    public int getPatientId() {
        return patientId;
    }

    @Override
    public String toString() {
        return "Patient ID   : " + patientId +
                "\nPatient Name : " + patientName +
                "\nTreatment    : " + treatmentDetails +
                "\nCompleted On : " + completionDate;
    }
}
