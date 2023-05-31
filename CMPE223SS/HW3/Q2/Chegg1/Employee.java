package CMPE223SS.HW3.Q2.Chegg1;

class Employee {
    int id;
    String name;
    boolean gender;

    public Employee(int id, String name, boolean gender) {
        this.id = id;
        this.name = name;
        this.gender = gender;
    }

    public String toString() {
        return id + " " + name + " " + (gender ? "Female" : "Male");
    }
}