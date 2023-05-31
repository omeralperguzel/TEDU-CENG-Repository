//-----------------------------------------------------
//Title: Main
//Author: Ömer Alper Güzel
//Section: 2
//Assignment: 3 Q2
//Description: This is a Java program that allows the user to use functions needed for employee database.
//-----------------------------------------------------

package CMPE223SS.HW3.Q2;

class EmployeeDatabase {
    Node root;

    // This is the constructor of the class EmployeeDatabase
    public EmployeeDatabase() {
        this.root = null;
    }

    // This is the insert method used for inserting a new employee into the database
    public void insertEmployee(int id, String name, boolean gender) {
        Employee employee = new Employee(id, name, gender);
        Node node = new Node(employee);

        if (root == null) {
            root = node;
            return;
        }

        Node current = root;
        Node parent = null;

        while (true) {
            parent = current;

            if (id < current.employee.id) {
                current = current.left;

                if (current == null) {
                    parent.left = node;
                    return;
                }
            } else {
                current = current.right;

                if (current == null) {
                    parent.right = node;
                    return;
                }
            }
        }
    }

    //This is the delete method used for deleting an employee from the database
    public void deleteEmployee(int id) {
        root = deleteNode(root, id);
    }

    //This is the deleteNode method used for deleting a node from the database
    private Node deleteNode(Node root, int id) {
        if (root == null) {
            return null;
        }

        if (id < root.employee.id) {
            root.left = deleteNode(root.left, id);
        } else if (id > root.employee.id) {
            root.right = deleteNode(root.right, id);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            Node temp = findMinNode(root.right);
            root.employee = temp.employee;
            root.right = deleteNode(root.right, temp.employee.id);
        }

        return root;
    }

    //This is the findMinNode method used for finding the minimum node in the database
    private Node findMinNode(Node node) {
        while (node.left != null) {
            node = node.left;
        }

        return node;
    }

    //This is the searchEmployee method used for searching an employee in the database
    public void searchEmployee(int id) {
        Node current = root;

        while (current != null) {
            if (id == current.employee.id) {
                System.out.println(current.employee);
                return;
            } 
            else if (id < current.employee.id) {
                current = current.left;
            } 
            else {
                current = current.right;
            }
        }

        System.out.println("No record found.");
    }

    //This is the listAllEmployees method used for listing all the employees in the database
    public void listAllEmployees() {
        
        inOrderTraversal(root);
    }

    //This is the inOrderTraversal method used for traversing the database in order
    private void inOrderTraversal(Node node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.println(node.employee);
            inOrderTraversal(node.right);
        }
    }

    //This is the listEmployeesInRange method used for listing the employees in a given range
    public void listEmployeesInRange(int minID, int maxID) {
        boolean found = inOrderTraversalInRange(root, minID, maxID);
        if (!found) {
            System.out.println("No record found.");
        }
    }

    //it is not a void anymore
    /*private boolean inOrderTraversalInRange(Node node, int minId, int maxId) {
        boolean found = false;
        if (node != null) {
            if (node.employee.id >= minId) {
                found = inOrderTraversalInRange(node.left, minId, maxId);
            }

            else if (node.employee.id >= minId && node.employee.id <= maxId) {
                found = true;
                inOrderTraversalInRange(node.left, minId, maxId);
                System.out.println(node.employee);
                inOrderTraversalInRange(node.right, minId, maxId);
            }

            else if (node.employee.id <= maxId) {
                found = inOrderTraversalInRange(node.right, minId, maxId);
            }
        }
        return found;
    }
}*/


//This is the inOrderTraversalInRange method used for traversing the database in order in a given range
private boolean inOrderTraversalInRange(Node node, int minID, int maxID) {
    boolean found = false;
    if (node != null) {
        if (node.employee.id >= minID && node.employee.id <= maxID) {
            found = true;
            inOrderTraversalInRange(node.left, minID, maxID);
            System.out.println(node.employee);
            inOrderTraversalInRange(node.right, minID, maxID);
        } 
        else if (node.employee.id > maxID) {
            found = inOrderTraversalInRange(node.left, minID, maxID);
        } 
        else if (node.employee.id < minID) {
            found = inOrderTraversalInRange(node.right, minID, maxID);
        }
    }
    return found;
}
}
