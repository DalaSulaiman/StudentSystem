import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentManager {
    // List to store Student objects dynamically
    private ArrayList<Student> studentList;
    private  final String FILE_NAME = "students.txt"; // file name to store data

    // Constructor to initialize the list
    public StudentManager() {
        studentList = new ArrayList<>();
        loadDataFromFile(); // Automatically load saved data when program starts
    }

    // Method to add a new student and save changes to file
    public void addStudent(Student student) {
        studentList.add(student);
        saveDataToFile();
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

    // Method to search for a student by ID +with Custom Exception
    public Student searchStudentById(int id) throws StudentNotFoundException {
        for (Student s : studentList) {
            if (s.getId() == id) {
                return s; // Return the student if found
            }
        }
  throw new StudentNotFoundException("Student with ID "+ id + " was not found in the system");
    }
    // Method to update student details by ID
    public boolean updateStudent(int id, String newName, double newGpa)throws StudentNotFoundException {
        Student student = searchStudentById(id);
            student.setName(newName);
            student.setGpa(newGpa);
            saveDataToFile(); // Save changes immediately to text file
            return true;
        }

    // Method to delete a student by ID
    public boolean deleteStudent(int id) throws StudentNotFoundException{
        Student s = searchStudentById(id);
            studentList.remove(s);
            saveDataToFile();
            return true; // Successfully deleted
    }
    // save student list to text file using printWriter
    private void  saveDataToFile(){
        try(PrintWriter writer=new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Student s: studentList){
             // save formatted line :ID,Name,GPA
             writer.println(s.getId()+ ","+s.getName()+","+s.getGpa());
            }
        }catch (IOException e){
            System.out.println("Error saving data to file :"+e.getMessage());
        }
    }

    //Load student list from text file using Scanner
    private void loadDataFromFile(){
        File file = new File(FILE_NAME);
        if (!file.exists()){
            return; // If file doesn't exist yet, do nothing
        }
        try (Scanner fileScanner = new Scanner(file)){
            while (fileScanner.hasNextLine()){
                String line = fileScanner.nextLine();
                String[] data = line.split(","); //Split data by comma
                if (data.length==3){
                    int id = Integer.parseInt(data[0]);
                    String name = data[1];
                    double gpa = Double.parseDouble(data[2]);
                    studentList.add(new Student(id,name,gpa));
                }
            }
        }catch (FileNotFoundException e){
            System.out.println("File not found: "+e.getMessage());
        }
    }
    // Method to calculate and display system statistics
    public void displayStatistics() {
        if (studentList.isEmpty()) {
            System.out.println("No students available for statistics.");
            return;
        }

        double totalGpa = 0;
        double maxGpa = studentList.get(0).getGpa();
        double minGpa = studentList.get(0).getGpa();

        for (Student s : studentList) {
            double gpa = s.getGpa();
            totalGpa += gpa;

            if (gpa > maxGpa) maxGpa = gpa;
            if (gpa < minGpa) minGpa = gpa;
        }

        double averageGpa = totalGpa / studentList.size();

        System.out.println("\n=== System Statistics ===");
        System.out.println("Total Students: " + studentList.size());
        System.out.printf("Average GPA: %.2f\n", averageGpa);
        System.out.println("Highest GPA: " + maxGpa);
        System.out.println("Lowest GPA: " + minGpa);
    }
    // Method to sort students by GPA in descending order (Highest to Lowest)
    public void sortStudentsByGpa() {
        if (studentList.isEmpty()) {
            System.out.println("No students available to sort.");
            return;
        }

        // Using Lambda expression to compare GPA in descending order
        studentList.sort((s1, s2) -> Double.compare(s2.getGpa(), s1.getGpa()));
        saveDataToFile(); // Save sorted order to file
        System.out.println("Students sorted by GPA successfully!");
        displayAllStudents(); // Show the sorted list
    }

}