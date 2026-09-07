# Mini Hospital Emergency Management System

A console-based Java application built for **CIT300 - Data Structures and Algorithms**
(Individual Mid Assignment). The system simulates patient registration, emergency
treatment requests, treatment completion, and patient visit history using four
core data structures implemented from scratch (no built-in `java.util` collection
classes are used for the required structures).

## Data Structures Used

| Requirement                | Data Structure            | File                     |
|-----------------------------|----------------------------|--------------------------|
| Patient Records              | Binary Search Tree (BST)  | `PatientBST.java`        |
| Emergency Patient Queue      | Queue (FIFO)               | `EmergencyQueue.java`    |
| Treatment History            | Stack (LIFO)                | `TreatmentStack.java`   |
| Patient Visit History         | Singly Linked List         | `VisitHistory.java`     |

## Project Structure

```
hospital-emergency-system/
├── src/
│   ├── Patient.java                 # Patient record model
│   ├── PatientBST.java              # BST: insert, search, delete, in-order traversal
│   ├── EmergencyQueue.java          # Queue: enqueue, dequeue, display
│   ├── TreatmentRecord.java         # Completed treatment record model
│   ├── TreatmentStack.java          # Stack: push, pop, display
│   ├── Visit.java                   # Single visit history node
│   ├── VisitHistory.java            # Singly Linked List: add, remove, search, display
│   └── HospitalManagementSystem.java# Main class / console menu
├── README.md
└── .gitignore
```

## How Everything Connects

- **Register a patient** → inserted into the `PatientBST` and automatically added to
  the `EmergencyQueue`.
- **Call next patient** → dequeued from the front of `EmergencyQueue` (FIFO).
- **Complete treatment** → a `TreatmentRecord` is pushed onto `TreatmentStack` (LIFO),
  and a matching `Visit` is appended to that patient's own `VisitHistory`
  (singly linked list).
- **View a patient** → search the BST by Patient ID, then browse their personal
  visit history.

## How to Compile and Run

Requires JDK 17+ (tested on JDK 21).

```bash
cd src
javac *.java
java HospitalManagementSystem
```

## Main Menu

```
1. Patient Records (Binary Search Tree)
2. Emergency Patient Queue (Queue)
3. Treatment History (Stack)
4. Patient Visit History (Singly Linked List)
0. Exit
```

Each option opens a sub-menu with the specific operations required for that
data structure (insert/search/delete/traverse for BST, enqueue/dequeue/display
for the queue, push/pop/display for the stack, add/remove/search/display for
the linked list).

## Sample Usage Flow

1. Go to **Patient Records → Register New Patient** and enter patient details.
   The patient is stored in the BST and placed in the emergency queue.
2. Go to **Emergency Queue → Dequeue Next Patient** to start treating the
   first patient in line.
3. Go to **Treatment History → Complete Treatment** to log the finished
   treatment. This pushes a record onto the stack and adds an entry to the
   patient's visit history.
4. Go to **Patient Visit History**, enter the Patient ID, and choose
   **Display Full Visit History** to see all of that patient's past visits.

## Author

Student submission for CIT300 - Data Structures and Algorithms, Sri Lanka
Technology Campus.

## Academic Integrity Note

This repository represents my own individual implementation for the CIT300
Mid Assignment, developed and committed progressively as required by the
assignment brief.
