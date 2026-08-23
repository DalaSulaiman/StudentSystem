public class Student {
    private int id;
    private String name;
    private double gpa;

    // Constructor
    public Student(int id, String name, double gpa) {
        this.id = id;
        this.name=name;
        this.gpa=gpa;
    }
//Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGpa() {
        return gpa;
    }
    public void setGpa(double gpa){
        this.gpa=gpa;
    }
    //طباعة بيانات الطالب بشكل منظم
    @Override
    public String toString(){
        return "Student ID:  "+ id +" |  Name: "+ name +" |  GPA: "+gpa;
    }
}