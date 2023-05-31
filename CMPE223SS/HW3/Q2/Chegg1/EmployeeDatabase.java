package CMPE223SS.HW3.Q2.Chegg1;

class EmployeeDatabase {
    Node root;

    public EmployeeDatabase() {
        root = null;
    }

    public void insertEmployee(int id, String name, boolean gender) {
        Employee newEmployee = new Employee(id, name, gender);
        root = insertHelper(root, newEmployee);
    }

    private Node insertHelper(Node root, Employee employee) {
        if (root == null) {
            return new Node(employee);
        }

        if (employee.id < root.employee.id) {
            root.left = insertHelper(root.left, employee);
        } else if (employee.id > root.employee.id) {
            root.right = insertHelper(root.right, employee);
        }

        return root;
    }

    public void deleteEmployee(int id) {
        root = deleteHelper(root, id);
    }

    private Node deleteHelper(Node root, int id) {
        if (root == null) {
            return null;
        }

        if (id < root.employee.id) {
            root.left = deleteHelper(root.left, id);
        } else if (id > root.employee.id) {
            root.right = deleteHelper(root.right, id);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            Node successor = findSuccessor(root.right);
            root.employee = successor.employee;
            root.right = deleteHelper(root.right, successor.employee.id);
        }

        return root;
    }

    private Node findSuccessor(Node root) {
        Node current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    public void searchEmployee(int id) {
        Node result = searchHelper(root, id);
        if (result == null) {
            System.out.println("Record not found.");
        } else {
            System.out.println(result.employee);
        }
    }

    private Node searchHelper(Node root, int id) {
        if (root == null || root.employee.id == id) {
            return root;
        }

        if (id < root.employee.id) {
            return searchHelper(root.left, id);
        } else {
            return searchHelper(root.right, id);
        }
    }

    public void listAllEmployees() {
        inOrderTraversal(root);
    }

    private void inOrderTraversal(Node root) {
        if (root != null) {
            inOrderTraversal(root.left);
            System.out.println(root.employee);
            inOrderTraversal(root.right);
        }
    }

    public void listEmployeesInRange(int minId, int maxId) {
        inOrderTraversalInRange(root, minId, maxId);
    }

    private void inOrderTraversalInRange(Node root, int minId, int maxId) {
        if (root != null) {
            if (root.employee.id >= minId) {
                inOrderTraversalInRange(root.left, minId, maxId);
                if (root.employee.id >= minId && root.employee.id <= maxId) {
                    System.out.println(root.employee);
                }
                inOrderTraversalInRange(root.right, minId, maxId);
            } else {
                inOrderTraversalInRange(root.right, minId, maxId);
            }
        }
    }
}