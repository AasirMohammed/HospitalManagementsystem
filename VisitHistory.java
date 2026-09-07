/**
 * VisitHistory.java
 *
 * A Singly Linked List implementation used to store a patient's
 * previous hospital visits.
 *
 * Supports:
 *  - addVisit()      : add a new visit at the end of the list
 *  - removeVisit()   : remove a visit by visit ID
 *  - searchVisit()   : search for a visit by visit ID
 *  - displayHistory(): print all visits in order
 */
public class VisitHistory {
    private Visit head;
    private int size;

    public VisitHistory() {
        this.head = null;
        this.size = 0;
    }

    /** Add a new visit to the end of the linked list. */
    public void addVisit(Visit newVisit) {
        if (head == null) {
            head = newVisit;
        } else {
            Visit current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newVisit;
        }
        size++;
    }

    /** Remove a visit by its visit ID. Returns true if removed. */
    public boolean removeVisit(int visitId) {
        if (head == null) {
            return false;
        }

        // If the head itself needs removal
        if (head.getVisitId() == visitId) {
            head = head.next;
            size--;
            return true;
        }

        Visit current = head;
        while (current.next != null) {
            if (current.next.getVisitId() == visitId) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        return false; // not found
    }

    /** Search for a visit by visit ID. Returns null if not found. */
    public Visit searchVisit(int visitId) {
        Visit current = head;
        while (current != null) {
            if (current.getVisitId() == visitId) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    /** Display the entire visit history in order. */
    public void displayHistory() {
        if (head == null) {
            System.out.println("  No visit history available.");
            return;
        }
        Visit current = head;
        int count = 1;
        while (current != null) {
            System.out.println("  --- Visit #" + count + " ---");
            System.out.println(current);
            current = current.next;
            count++;
        }
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int getSize() {
        return size;
    }
}
