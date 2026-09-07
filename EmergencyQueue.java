/**
 * EmergencyQueue.java
 *
 * A custom Queue (FIFO) implementation, built using a singly linked
 * structure of QueueNode objects, used to manage patients waiting
 * for emergency treatment.
 *
 * Supports:
 *  - enqueue() : add a patient to the back of the queue
 *  - dequeue() : remove the patient from the front of the queue
 *  - displayQueue() : show all waiting patients
 *  - isEmpty() : check whether the queue has no patients
 */
public class EmergencyQueue {

    /** Internal node class for the queue. */
    private static class QueueNode {
        Patient patient;
        QueueNode next;

        QueueNode(Patient patient) {
            this.patient = patient;
        }
    }

    private QueueNode front;
    private QueueNode rear;
    private int size;

    public EmergencyQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    /** Add a patient to the back of the queue. */
    public void enqueue(Patient patient) {
        QueueNode newNode = new QueueNode(patient);
        if (rear == null) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    /** Remove and return the patient at the front of the queue. */
    public Patient dequeue() {
        if (isEmpty()) {
            System.out.println("The emergency queue is empty. No patient to treat.");
            return null;
        }
        Patient removedPatient = front.patient;
        front = front.next;
        if (front == null) {
            rear = null; // queue became empty
        }
        size--;
        return removedPatient;
    }

    /** Display all patients currently waiting, front to back. */
    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("No patients currently waiting in the emergency queue.");
            return;
        }
        QueueNode current = front;
        int position = 1;
        while (current != null) {
            System.out.println(position + ". Patient ID: " + current.patient.getPatientId() +
                    " | Name: " + current.patient.getName() +
                    " | Condition: " + current.patient.getMedicalCondition());
            current = current.next;
            position++;
        }
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int getSize() {
        return size;
    }
}
