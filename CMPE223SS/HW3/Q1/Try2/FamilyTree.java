package CMPE223SS.HW3.Q1.Try2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class FamilyTree {
    Person root;

    public FamilyTree(Person root) {
        this.root = root;
    }

    public static FamilyTree createFamilyTree(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        Map<Integer, Person> idToPerson = new HashMap<>();
        while (scanner.hasNextLine()) {
            String[] parts = scanner.nextLine().split(" ");
            int id1 = Integer.parseInt(parts[0]);
            String name1 = parts[1];
            int id2 = Integer.parseInt(parts[2]);
            String name2 = parts[3];
            Person person1 = idToPerson.getOrDefault(id1, new Person(name1, id1));
            Person person2 = idToPerson.getOrDefault(id2, new Person(name2, id2));
            person2.children.add(person1);
            idToPerson.put(id1, person1);
            idToPerson.put(id2, person2);
        }
        scanner.close();
        return new FamilyTree(idToPerson.get(0));
    }

    public void printAllDescendants(int id) {
        Person person = findPerson(root, id);
        if (person != null) {
            List<Person> descendants = new ArrayList<>();
            collectDescendants(person, descendants);
            Collections.sort(descendants, Comparator.comparingInt(p -> p.id));
            for (Person descendant : descendants) {
                System.out.println(descendant.name);
            }
        }
    }

    public boolean checkAncestor(int id1, int id2) {
        Person person1 = findPerson(root, id1);
        Person person2 = findPerson(root, id2);
        if (person1 != null && person2 != null) {
            return isAncestor(person1, person2);
        }
        return false;
    }

    public boolean checkDescendant(int id1, int id2) {
        Person person1 = findPerson(root, id1);
        Person person2 = findPerson(root, id2);
        if (person1 != null && person2 != null) {
            return isDescendant(person1, person2);
        }
        return false;
    }

    public boolean checkSibling(int id1, int id2) {
        Person person1 = findPerson(root, id1);
        Person person2 = findPerson(root, id2);
        if (person1 != null && person2 != null) {
            Person parent1 = findParent(root, person1);
            Person parent2 = findParent(root, person2);
            return parent1 != null && parent2 != null && parent1 == parent2;
        }
        return false;
    }

    public String findOldestCommonRelative(int id1, int id2) {
        Person person1 = findPerson(root, id1);
        Person person2 = findPerson(root, id2);
        if (person1 != null && person2 != null) {
            Set<Person> ancestors = new HashSet<>();
            collectAncestors(person1, ancestors);
            while (person2 != null) {
                if (ancestors.contains(person2)) {
                    return person2.name;
                }
                person2 = findParent(root, person2);
            }
        }
        return null;
    }

    private Person findPerson(Person person, int id) {
        if (person.id == id) {
            return person;
        }
        for (Person child : person.children) {
            Person found = findPerson(child, id);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    private void collectDescendants(Person person, List<Person> descendants) {
        descendants.add(person);
        for (Person child : person.children) {
            collectDescendants(child, descendants);
        }
    }

    private boolean isAncestor(Person ancestor, Person person) {
        if (ancestor == person) {
            return true;
        }
        for (Person child : ancestor.children) {
            if (isAncestor(child, person)) {
                return true;
            }
        }
        return false;
    }

    private boolean isDescendant(Person person, Person descendant) {
        if (person == descendant) {
            return true;
        }
        for (Person child : person.children) {
            if (isDescendant(child, descendant)) {
                return true;
            }
        }
        return false;
    }

    private Person findParent(Person parent, Person child) {
        for (Person p : parent.children) {
            if (p == child) {
                return parent;
            }
            Person found = findParent(p, child);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    private void collectAncestors(Person person, Set<Person> ancestors) {
        Person parent = findParent(root, person);
        if (parent != null) {
            ancestors.add(parent);
            collectAncestors(parent, ancestors);
        }
    }
}