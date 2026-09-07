import java.util.Scanner;

/**
 * HospitalManagementSystem.java
 *
 * Main driver class - Mini Hospital Emergency Management System.
 *
 * Ties together all four required data structures:
 *  1. PatientBST      - stores permanent patient records (BST)
 *  2. EmergencyQueue   - manages patients waiting for treatment (Queue)
 *  3. TreatmentStack   - stores completed treatment records (Stack)
 *  4. VisitHistory     - each patient's past visits (Singly Linked List)
 *
 * Typical flow:
 *   Register patient  -> stored in BST + added to Emergency Queue
 *   Call next patient  -> dequeued from Emergency Queue for treatment
 *   Complete treatment -> pushed onto Treatment Stack + logged as a Visit
 *   View patient       -> search BST, browse their Visit History
 */
public class Main {

    private static final PatientBST patientBST = new PatientBST();
    private static final EmergencyQueue emergencyQueue = new EmergencyQueue();
    private static final TreatmentStack treatmentStack = new TreatmentStack();
    private static final Scanner scanner = new Scanner(System.in);

    // Simple auto-incrementing IDs for demo convenience
    private static int nextVisitId = 1000;

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1 -> patientRecordsMenu();
                case 2 -> emergencyQueueMenu();
                case 3 -> treatmentHistoryMenu();
                case 4 -> visitHistoryMenu();
                case 0 -> {
                    running = false;
                    System.out.println("Exiting system. Goodbye!");
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    // ==================== MAIN MENU ====================

    private static void printMainMenu() {
        System.out.println("\n================================================");
        System.out.println(" MINI HOSPITAL EMERGENCY MANAGEMENT SYSTEM");
        System.out.println("================================================");
        System.out.println("1. Patient Records (Binary Search Tree)");
        System.out.println("2. Emergency Patient Queue (Queue)");
        System.out.println("3. Treatment History (Stack)");
        System.out.println("4. Patient Visit History (Singly Linked List)");
        System.out.println("0. Exit");
        System.out.println("================================================");
    }

    // ==================== 1. PATIENT BST MENU ====================

    private static void patientRecordsMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n---- PATIENT RECORDS (BST) ----");
            System.out.println("1. Register New Patient");
            System.out.println("2. Search Patient by ID");
            System.out.println("3. Delete Patient by ID");
            System.out.println("4. Display All Patients (In-order Traversal)");
            System.out.println("0. Back to Main Menu");
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1 -> registerPatient();
                case 2 -> searchPatient();
                case 3 -> deletePatient();
                case 4 -> {
                    System.out.println("\n--- All Patients (ascending Patient ID) ---");
                    patientBST.inorderDisplay();
                }
                case 0 -> back = true;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void registerPatient() {
        System.out.println("\n--- Register New Patient ---");
        int id = readInt("Patient ID: ");

        if (patientBST.search(id) != null) {
            System.out.println("A patient with this ID already exists.");
            return;
        }

        String name = readString("Patient Name: ");
        int age = readInt("Age: ");
        String contact = readString("Contact Number: ");
        String condition = readString("Medical Condition: ");

        Patient patient = new Patient(id, name, age, contact, condition);
        patientBST.insert(patient);

        // Automatically add the newly registered patient to the emergency queue
        emergencyQueue.enqueue(patient);

        System.out.println("Patient registered successfully and added to the emergency queue.");
    }

    private static void searchPatient() {
        int id = readInt("Enter Patient ID to search: ");
        Patient patient = patientBST.search(id);
        if (patient == null) {
            System.out.println("No patient found with ID " + id);
        } else {
            System.out.println("\n--- Patient Found ---");
            System.out.println(patient);
        }
    }

    private static void deletePatient() {
        int id = readInt("Enter Patient ID to delete: ");
        boolean deleted = patientBST.delete(id);
        if (deleted) {
            System.out.println("Patient with ID " + id + " deleted successfully.");
        } else {
            System.out.println("No patient found with ID " + id);
        }
    }

    // ==================== 2. EMERGENCY QUEUE MENU ====================

    private static void emergencyQueueMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n---- EMERGENCY PATIENT QUEUE (Queue) ----");
            System.out.println("1. Enqueue Existing Patient (by ID)");
            System.out.println("2. Dequeue Next Patient for Treatment");
            System.out.println("3. Display Waiting Queue");
            System.out.println("0. Back to Main Menu");
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1 -> enqueueExistingPatient();
                case 2 -> dequeuePatient();
                case 3 -> {
                    System.out.println("\n--- Patients Currently Waiting ---");
                    emergencyQueue.displayQueue();
                }
                case 0 -> back = true;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void enqueueExistingPatient() {
        int id = readInt("Enter Patient ID to add to the queue: ");
        Patient patient = patientBST.search(id);
        if (patient == null) {
            System.out.println("No patient found with ID " + id + ". Please register the patient first.");
            return;
        }
        emergencyQueue.enqueue(patient);
        System.out.println("Patient " + patient.getName() + " added to the emergency queue.");
    }

    private static void dequeuePatient() {
        Patient patient = emergencyQueue.dequeue();
        if (patient != null) {
            System.out.println("Now treating: " + patient.getName() + " (ID: " + patient.getPatientId() + ")");
        }
    }

    // ==================== 3. TREATMENT STACK MENU ====================

    private static void treatmentHistoryMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n---- TREATMENT HISTORY (Stack) ----");
            System.out.println("1. Complete Treatment for a Patient (Push)");
            System.out.println("2. Undo Last Completed Treatment Record (Pop)");
            System.out.println("3. Display Treatment Records");
            System.out.println("0. Back to Main Menu");
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1 -> completeTreatment();
                case 2 -> popTreatment();
                case 3 -> {
                    System.out.println("\n--- Treatment Records (most recent first) ---");
                    treatmentStack.displayStack();
                }
                case 0 -> back = true;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void completeTreatment() {
        int id = readInt("Enter Patient ID whose treatment is complete: ");
        Patient patient = patientBST.search(id);
        if (patient == null) {
            System.out.println("No patient found with ID " + id);
            return;
        }

        String treatmentDetails = readString("Enter treatment details: ");
        String date = readString("Enter completion date (e.g., 2026-09-06): ");

        // 1. Push onto the Treatment Stack
        TreatmentRecord record = new TreatmentRecord(id, patient.getName(), treatmentDetails, date);
        treatmentStack.push(record);

        // 2. Log this as a new Visit in the patient's Visit History (linked list)
        String doctor = readString("Enter attending doctor's name: ");
        Visit visit = new Visit(nextVisitId++, date, doctor, patient.getMedicalCondition(), treatmentDetails);
        patient.getVisitHistory().addVisit(visit);

        System.out.println("Treatment completed, pushed to stack, and logged in visit history.");
    }

    private static void popTreatment() {
        TreatmentRecord record = treatmentStack.pop();
        if (record != null) {
            System.out.println("Removed most recent treatment record:");
            System.out.println(record);
        }
    }

    // ==================== 4. VISIT HISTORY (LINKED LIST) MENU ====================

    private static void visitHistoryMenu() {
        int id = readInt("Enter Patient ID to manage visit history: ");
        Patient patient = patientBST.search(id);
        if (patient == null) {
            System.out.println("No patient found with ID " + id);
            return;
        }

        boolean back = false;
        while (!back) {
            System.out.println("\n---- VISIT HISTORY for " + patient.getName() + " (Singly Linked List) ----");
            System.out.println("1. Add New Visit");
            System.out.println("2. Remove Visit by Visit ID");
            System.out.println("3. Search Visit by Visit ID");
            System.out.println("4. Display Full Visit History");
            System.out.println("0. Back to Main Menu");
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1 -> addVisit(patient);
                case 2 -> removeVisit(patient);
                case 3 -> searchVisit(patient);
                case 4 -> {
                    System.out.println("\n--- Visit History ---");
                    patient.getVisitHistory().displayHistory();
                }
                case 0 -> back = true;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addVisit(Patient patient) {
        String date = readString("Visit Date (e.g., 2026-09-06): ");
        String doctor = readString("Doctor Name: ");
        String diagnosis = readString("Diagnosis: ");
        String treatment = readString("Treatment: ");

        Visit visit = new Visit(nextVisitId++, date, doctor, diagnosis, treatment);
        patient.getVisitHistory().addVisit(visit);
        System.out.println("Visit added with Visit ID: " + visit.getVisitId());
    }

    private static void removeVisit(Patient patient) {
        int visitId = readInt("Enter Visit ID to remove: ");
        boolean removed = patient.getVisitHistory().removeVisit(visitId);
        System.out.println(removed ? "Visit removed successfully." : "Visit ID not found.");
    }

    private static void searchVisit(Patient patient) {
        int visitId = readInt("Enter Visit ID to search: ");
        Visit visit = patient.getVisitHistory().searchVisit(visitId);
        if (visit == null) {
            System.out.println("Visit ID not found.");
        } else {
            System.out.println("\n--- Visit Found ---");
            System.out.println(visit);
        }
    }

    // ==================== INPUT HELPERS ====================

    private static int readInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // consume leftover newline
        return value;
    }

    private static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
