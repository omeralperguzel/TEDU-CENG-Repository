package CMPE223SS.HW3.Q2.Chegg1;

class Node {
    Employee employee;
    Node left;
    Node right;

    public Node(Employee employee) {
        this.employee = employee;
        this.left = null;
        this.right = null;
    }
}