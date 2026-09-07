/**
 * PatientBST.java
 *
 * Binary Search Tree (BST) used to store Patient records,
 * keyed by Patient ID.
 *
 * Supports:
 *  - insert() : insert a new patient
 *  - search() : search for a patient by ID
 *  - delete() : delete a patient by ID
 *  - inorderDisplay() : in-order traversal (ascending Patient ID order)
 */
public class PatientBST {

    /** Internal node class for the BST. */
    private static class BSTNode {
        Patient patient;
        BSTNode left, right;

        BSTNode(Patient patient) {
            this.patient = patient;
        }
    }

    private BSTNode root;

    public PatientBST() {
        root = null;
    }

    // ---------- INSERT ----------

    public void insert(Patient patient) {
        root = insertRec(root, patient);
    }

    private BSTNode insertRec(BSTNode node, Patient patient) {
        if (node == null) {
            return new BSTNode(patient);
        }

        if (patient.getPatientId() < node.patient.getPatientId()) {
            node.left = insertRec(node.left, patient);
        } else if (patient.getPatientId() > node.patient.getPatientId()) {
            node.right = insertRec(node.right, patient);
        } else {
            // Duplicate ID - update the existing record instead
            System.out.println("A patient with ID " + patient.getPatientId() +
                    " already exists. Updating existing record is not performed automatically.");
        }
        return node;
    }

    // ---------- SEARCH ----------

    public Patient search(int patientId) {
        BSTNode result = searchRec(root, patientId);
        return (result == null) ? null : result.patient;
    }

    private BSTNode searchRec(BSTNode node, int patientId) {
        if (node == null || node.patient.getPatientId() == patientId) {
            return node;
        }
        if (patientId < node.patient.getPatientId()) {
            return searchRec(node.left, patientId);
        }
        return searchRec(node.right, patientId);
    }

    // ---------- DELETE ----------

    public boolean delete(int patientId) {
        if (search(patientId) == null) {
            return false;
        }
        root = deleteRec(root, patientId);
        return true;
    }

    private BSTNode deleteRec(BSTNode node, int patientId) {
        if (node == null) {
            return null;
        }

        if (patientId < node.patient.getPatientId()) {
            node.left = deleteRec(node.left, patientId);
        } else if (patientId > node.patient.getPatientId()) {
            node.right = deleteRec(node.right, patientId);
        } else {
            // Node found - handle the three deletion cases

            // Case 1: no children
            if (node.left == null && node.right == null) {
                return null;
            }

            // Case 2: one child
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }

            // Case 3: two children
            // Find the in-order successor (smallest value in the right subtree)
            BSTNode successor = findMin(node.right);
            node.patient = successor.patient;
            node.right = deleteRec(node.right, successor.patient.getPatientId());
        }
        return node;
    }

    private BSTNode findMin(BSTNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // ---------- IN-ORDER TRAVERSAL ----------

    public void inorderDisplay() {
        if (root == null) {
            System.out.println("No patient records found.");
            return;
        }
        inorderRec(root);
    }

    private void inorderRec(BSTNode node) {
        if (node == null) {
            return;
        }
        inorderRec(node.left);
        System.out.println("--------------------------------");
        System.out.println(node.patient);
        inorderRec(node.right);
    }

    public boolean isEmpty() {
        return root == null;
    }
}
