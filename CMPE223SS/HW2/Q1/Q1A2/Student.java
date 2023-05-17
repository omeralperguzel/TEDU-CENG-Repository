package CMPE223SS.HW2.Q1.Q1A2;

public class Student implements Comparable<Student> {
    private String name;
    private long id;
    private int semesterNo;

    public Student(String name, long id, int semesterNo) {
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

    @Override
    public int compareTo(Student o) {
        if (this.id != o.id) {
            return Long.compare(this.id, o.id);
        } else if (this.semesterNo != o.semesterNo) {
            return Integer.compare(this.semesterNo, o.semesterNo);
        } else {
            return this.name.compareTo(o.name);
        }
    }
}