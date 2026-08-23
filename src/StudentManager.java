import java.util.ArrayList;

public class StudentManager {
    // List to store Student objects dynamically
    private ArrayList<Student> studentList;

    // Constructor to initialize the list
    public StudentManager() {
        studentList = new ArrayList<>();
    }

    // Method to add a new student
    public void addStudent(Student student) {
        studentList.add(student);
        System.out.println("Student added successfully!");
    }

    // Method to display all students
    public void displayAllStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        System.out.println("\n--- All Students List ---");
        for (Student s : studentList) {
            System.out.println(s);
        }
    }

    // Method to search for a student by ID
    public Student searchStudentById(int id) {
        for (Student s : studentList) {
            if (s.getId() == id) {
                return s; // Return the student if found
            }
        }
        return null; // Return null if student does not exist
    }

    // Method to delete a student by ID
    public boolean deleteStudent(int id) {
        Student s = searchStudentById(id);
        if (s != null) {
            studentList.remove(s);
            return true; // Successfully deleted
        }
        return false; // Student not found
    }
}
