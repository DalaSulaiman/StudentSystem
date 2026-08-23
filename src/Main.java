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

            if (!scanner.hasNextLine()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();

            switch (choice) {
                case 1: // Add Student with Validation
                    int id;
                    while (true) {
                        System.out.print("Enter Student ID: ");
                        if (scanner.hasNextInt()) {
                            id = scanner.nextInt();
                            //check if ID already exists
                            if (manager.searchStudentById(id) != null) {
                                System.out.println("Error: Students ID already exists! Try another ID");
                            } else {
                                break;
                            }
                        } else {
                            System.out.println("Invalid ID! Please enter number only");
                            scanner.next();
                        }
                    }
                    scanner.nextLine(); //Clear buffer


                    String name;
                    while (true) {
                        System.out.print("Enter Student Name: ");
                        name = scanner.nextLine().trim();

                        // Check if name contains only letters and spaces, and is not empty
                        if (name.matches("^[a-zA-Z\\s]+$")) {
                            break; // Valid name
                        } else {
                            System.out.println("Invalid Name! Please enter letters only (no numbers or special characters).");
                        }
                    }

                    double gpa;
                    while (true) {
                        System.out.print("Enter Student GPA(0.0 to 5.0): ");
                        if (scanner.hasNextDouble()) {
                            gpa = scanner.nextDouble();
                            if (gpa >= 0.0 && gpa <= 5.0) {
                                break; //Valid GPA
                            } else {
                                System.out.println("Invalid GPA! Must be between 0.0 and 5.0.");
                            }
                            }else{
                                System.out.println("Invalid input! Please enter a valid decimal number.");
                                scanner.next();
                            }
                        }
                        manager.addStudent(new Student(id, name, gpa));
                        break;

                        case 2: // Display All Students
                            manager.displayAllStudents();
                            break;

                        case 3: // Search Student
                            System.out.print("Enter Student ID to Search: ");
                            if (scanner.hasNextInt()) {
                                int searchId = scanner.nextInt();
                                Student foundStudent = manager.searchStudentById(searchId);
                                if (foundStudent != null) {
                                    System.out.println("Student Found: " + foundStudent);
                                } else {
                                    System.out.println("Student with ID " + searchId + " not found.");
                                }
                            } else {
                                System.out.println("Invalid ID format.");
                                scanner.next();
                            }
                            break;
                        case 4: // Delete Student
                            System.out.print("Enter Student ID to Delete: ");
                            if (scanner.hasNextInt()) {
                                int deleteId = scanner.nextInt();
                                boolean isDeleted = manager.deleteStudent(deleteId);
                                if (isDeleted) {
                                    System.out.println("Student deleted successfully!");
                                } else {
                                    System.out.println("Student with ID " + deleteId + " not found.");
                                }
                            } else {
                                System.out.println("Invalid ID format");
                                scanner.next();
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
