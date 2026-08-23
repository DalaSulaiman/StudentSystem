import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            // Display main menu options
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Delete Student by ID");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1: // Add Student
                    System.out.print("Enter Student ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Clear buffer

                    System.out.print("Enter Student Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter Student GPA: ");
                    double gpa = scanner.nextDouble();

                    manager.addStudent(new Student(id, name, gpa));
                    break;

                case 2: // Display All Students
                    manager.displayAllStudents();
                    break;

                case 3: // Search Student
                    System.out.print("Enter Student ID to Search: ");
                    int searchId = scanner.nextInt();
                    Student foundStudent = manager.searchStudentById(searchId);

                    if (foundStudent != null) {
                        System.out.println("Student Found: " + foundStudent);
                    } else {
                        System.out.println("Student with ID " + searchId + " not found.");
                    }
                    break;

                case 4: // Delete Student
                    System.out.print("Enter Student ID to Delete: ");
                    int deleteId = scanner.nextInt();
                    boolean isDeleted = manager.deleteStudent(deleteId);

                    if (isDeleted) {
                        System.out.println("Student deleted successfully!");
                    } else {
                        System.out.println("Student with ID " + deleteId + " not found.");
                    }
                    break;

                case 5: // Exit application
                    running = false;
                    System.out.println("Exiting application... Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}