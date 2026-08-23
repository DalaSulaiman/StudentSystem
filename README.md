# Student Management System

A Java-based console application designed to manage student records efficiently. The system provides core CRUD operations, automated file handling for data persistence, and strict input validation.

## Features
* **Add Student:** Register new students with automatic validation (unique ID, valid string name, GPA between 0.0 and 5.0).
* **Display Students:** View all currently stored student records in a formatted list.
* **Search Student:** Find specific student details instantly by ID.
* **Delete Student:** Remove student records by ID with automatic file updates.
* **Data Persistence:** Automatically saves and loads records using a local text file (`students.txt`).

## Future Improvements
* **Update Student Information:** Allow modifying existing student details (Name, GPA).
* **Sorting Capabilities:** Sort students by GPA (highest to lowest) or alphabetically by name.
* **System Statistics:** Display average, highest, and lowest GPA metrics.  

## Tech Stack
* **Language:** Java (JDK 17+)
* **Data Structures:** `ArrayList`
* **File I/O:** `PrintWriter`, `Scanner`
* **Version Control:** Git & GitHub

## How to Run
1. Clone the repository:
   ```bash
   git clone [https://github.com/YOUR_USERNAME/StudentSystem.git](https://github.com/YOUR_USERNAME/StudentSystem.git)
