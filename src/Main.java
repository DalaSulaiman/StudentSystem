import java.util.Scanner;

public class Main {

    // ANSI Color Codes for Pretty Console Output
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";

    public static void main(String[] args) throws StudentNotFoundException {
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
            System.out.println("5. Update Student by ID");
            System.out.println("6. Display Statistics");
            System.out.println("7. Sort Students by GPA");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            if (!scanner.hasNextLine()) {
                System.out.println(YELLOW +"Invalid input. Please enter a number."+ RESET);
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
                            try {
                                if (manager.searchStudentById(id) != null) {
                                    System.out.println(RED +"Error: Student ID exists! Try another ID."+RESET);
                                }
                            } catch (StudentNotFoundException e) {
                                break;
                            }

                        } else {
                            System.out.println(YELLOW +"Invalid ID! Please enter number only"+RESET);
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
                            System.out.println(YELLOW+"Invalid Name! Please enter letters only (no numbers or special characters)."+RESET);
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
                                System.out.println(YELLOW+"Invalid GPA! Must be between 0.0 and 5.0."+RESET);
                            }
                            }else{
                                System.out.println(YELLOW+"Invalid input! Please enter a valid decimal number."+RESET);
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

                                int searchId = scanner.nextInt();
                                try {
                                    Student foundStudent = manager.searchStudentById(searchId);
                                    System.out.println("Student Found: "+ foundStudent);
                                }catch (StudentNotFoundException e){
                                    System.out.println("Error: "+e.getMessage());
                                }
                            break;
                        case 4: // Delete Student
                            System.out.print("Enter Student ID to Delete: ");
                            if (scanner.hasNextInt()) {
                                int deleteId = scanner.nextInt();
                                try {
                                    boolean isDeleted = manager.deleteStudent(deleteId);
                                    if (isDeleted) {
                                        System.out.println(GREEN+"Student deleted successfully!"+RESET);
                                    }
                                } catch (StudentNotFoundException e) {
                                    System.out.println("Error: " + e.getMessage());
                                }

                            } else {
                                System.out.println(YELLOW+"Invalid ID format"+RESET);
                                scanner.next();
                            }
                            break;

                case 5: // Update Student
                    System.out.print("Enter Student ID to Update: ");
                    if (scanner.hasNextInt()) {
                        int updateId = scanner.nextInt();
                        Student existing = null;
                        try {
                            existing = manager.searchStudentById(updateId);
                        } catch (StudentNotFoundException e) {

                        }
                        if (existing != null) {
                            scanner.nextLine(); // Clear buffer

                            // Validate New Name
                            String newName;
                            while (true) {
                                System.out.print("Enter New Name: ");
                                newName = scanner.nextLine().trim();
                                if (newName.matches("^[a-zA-Z\\s]+$")) {
                                    break;
                                } else {
                                    System.out.println(YELLOW+"Invalid Name! Letters only."+RESET);
                                }
                            }

                            // Validate New GPA
                            double newGpa;
                            while (true) {
                                System.out.print("Enter New GPA (0.0 to 5.0): ");
                                if (scanner.hasNextDouble()) {
                                    newGpa = scanner.nextDouble();
                                    if (newGpa >= 0.0 && newGpa <= 5.0) {
                                        break;
                                    } else {
                                        System.out.println(YELLOW+"Invalid GPA! Must be between 0.0 and 5.0."+RESET);
                                    }
                                } else {
                                    System.out.println(YELLOW+"Invalid input!"+RESET);
                                    scanner.next();
                                }
                            }

                            manager.updateStudent(updateId, newName, newGpa);
                            System.out.println(GREEN+"Student updated successfully!"+RESET);
                        } else {
                            System.out.println("Student with ID " + updateId + " not found.");
                        }
                    } else {
                        System.out.println(YELLOW+"Invalid ID format."+RESET);
                        scanner.next();
                    }
                    break;
                case 6: // Display Statistics
                    manager.displayStatistics();
                    break;

                case 7: // Sort Students by GPA
                    manager.sortStudentsByGpa();
                    break;

                    case 8: // Exit application
                            running = false;
                            System.out.println("Exiting application... Goodbye!");
                            break;

                        default:
                            System.out.println(YELLOW+"Invalid choice. Please try again."+RESET);
                    }
            }
            scanner.close();
        }
    }
