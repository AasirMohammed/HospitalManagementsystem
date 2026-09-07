/**
 * Visit.java
 *
 * Represents one node of a patient's Visit History Singly Linked List.
 * Each Visit stores details of a single past hospital visit.
 */
public class Visit {
    private int visitId;
    private String visitDate;
    private String doctorName;
    private String diagnosis;
    private String treatment;

    // Pointer to the next node in the singly linked list
    Visit next;

    public Visit(int visitId, String visitDate, String doctorName, String diagnosis, String treatment) {
        this.visitId = visitId;
        this.visitDate = visitDate;
        this.doctorName = doctorName;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.next = null;
    }

    public int getVisitId() {
        return visitId;
    }

    @Override
    public String toString() {
        return "  Visit ID  : " + visitId +
                "\n  Date      : " + visitDate +
                "\n  Doctor    : " + doctorName +
                "\n  Diagnosis : " + diagnosis +
                "\n  Treatment : " + treatment;
    }
}
