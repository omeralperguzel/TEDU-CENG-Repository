//-----------------------------------------------------
//Title: Book
//Author: Ömer Alper Güzel
//Section: 2
//Assignment: 4
//Description: This is a Java program that allows the user to manage a library by getting and setting information about the books.
//-----------------------------------------------------

class Book {
    // This is the declaration of the variables that will be used in this program.
    private String author;
    private String name;
    private int numCopies;

    // This is the constructor of the Book class.
    public Book(String author, String name, int numCopies) {
        this.author = author;
        this.name = name;
        this.numCopies = numCopies;
    }

    // This is the getter method for the author.
    public String getAuthor() {
        return author;
    }

    // This is the getter method for the name.
    public String getName() {
        return name;
    }

    // This is the getter method for the number of copies.
    public int getNumCopies() {
        return numCopies;
    }

    // This is the setter method for the number of copies.
    public void setNumCopies(int numCopies) {
        this.numCopies = numCopies;
    }
}