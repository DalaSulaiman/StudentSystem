# 🎓 Student Management System (Java Swing GUI)

A lightweight, Desktop-based **Student Management System** built with **Java** and **Swing**. It provides an intuitive GUI dashboard for managing student records, tracking academic statistics, and persisting data to a local text file.

---

## ✨ Features

- **📊 Dynamic Dashboard & Statistics:**
  - Tracks total student count, average GPA, and highest GPA in real-time.
- **🔍 Record Search & Sorting:**
  - Search students instantly by their 9-digit University ID.
  - Sort student records by GPA in descending order (Highest to Lowest).
- **📝 Form Validation:**
  - Enforces strict 9-digit university student ID format.
  - Validates full name format (alphabetic characters only).
  - Constrains GPA values between `0.0` and `5.0`.
  - Prevents duplicate Student IDs.
- **💾 Data Persistence:**
  - Reads and writes all student records from/to a local `students.txt` file automatically.
- **🎨 Modern & High-Contrast UI:**
  - Native system Look-and-Feel with accessible, color-coded buttons.

---

## 🛠️ Tech Stack

- **Language:** Java (JDK 8 or higher)
- **GUI Framework:** Java Swing (`JFrame`, `JTable`, `JPanel`)
- **Data Persistence:** File I/O (`BufferedReader`, `BufferedWriter`)
- **Version Control:** Git & GitHub

---

## 📁 Project Structure

```text
├── src/
│   ├── Student.java                 # Student entity model
│   ├── StudentManager.java          # Core logic, File I/O, & data operations
│   ├── StudentNotFoundException.java # Custom exception for missing student IDs
│   └── StudentFrame.java            # Swing GUI Application entry point
├── students.txt                     # Data storage file
└── README.md                        # Project documentation
