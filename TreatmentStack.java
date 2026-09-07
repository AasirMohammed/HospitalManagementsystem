/**
 * TreatmentStack.java
 *
 * A custom Stack (LIFO) implementation, built using a singly linked
 * structure of StackNode objects, used to store completed treatment
 * records.
 *
 * Supports:
 *  - push() : add a completed treatment record on top of the stack
 *  - pop()  : remove and return the most recently completed record
 *  - displayStack() : show all records, most recent first
 *  - isEmpty() : check whether the stack has no records
 */
public class TreatmentStack {

    /** Internal node class for the stack. */
    private static class StackNode {
        TreatmentRecord record;
        StackNode next;

        StackNode(TreatmentRecord record) {
            this.record = record;
        }
    }

    private StackNode top;
    private int size;

    public TreatmentStack() {
        top = null;
        size = 0;
    }

    /** Push a new treatment record onto the top of the stack. */
    public void push(TreatmentRecord record) {
        StackNode newNode = new StackNode(record);
        newNode.next = top;
        top = newNode;
        size++;
    }

    /** Pop (remove and return) the most recently completed treatment record. */
    public TreatmentRecord pop() {
        if (isEmpty()) {
            System.out.println("The treatment history stack is empty. Nothing to pop.");
            return null;
        }
        TreatmentRecord poppedRecord = top.record;
        top = top.next;
        size--;
        return poppedRecord;
    }

    /** Display all treatment records, most recent (top) first. */
    public void displayStack() {
        if (isEmpty()) {
            System.out.println("No treatment records found in the stack.");
            return;
        }
        StackNode current = top;
        int position = 1;
        while (current != null) {
            System.out.println("--- Record #" + position + " (Top = most recent) ---");
            System.out.println(current.record);
            current = current.next;
            position++;
        }
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int getSize() {
        return size;
    }
}
