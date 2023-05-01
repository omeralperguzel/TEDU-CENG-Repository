package CMPE223SS.HW2.Q1.Q1A1;

public class Student implements Comparable<Student> {
    private String name;
    private int id;
    private int semesterNo;

    public Student(String name, int id, int semesterNo) {
        this.name = name;
        this.id = id;
        this.semesterNo = semesterNo;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public int getSemesterNo() {
        return semesterNo;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setSemesterNo(int semesterNo) {
        this.semesterNo = semesterNo;
    }
    
    @Override
    public String toString() {
        return name + ", " + id + ", " + semesterNo;
    }

    @Override
    public int compareTo(Student other) {
        // First compare by id
        int result = Long.compare(this.id, other.id);
        if (result != 0) {
            return result;
        }
        
        // If id is the same, compare by semesterNo
        result = Integer.compare(this.semesterNo, other.semesterNo);
        if (result != 0) {
            return result;
        }

        // If semesterNo is the same, compare by name
        return this.name.compareTo(other.name);
    }
}
