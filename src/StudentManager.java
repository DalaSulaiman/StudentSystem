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

}
