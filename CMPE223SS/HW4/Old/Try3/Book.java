package Try3;

class Book {
    private String writer;
    private String name;
    private int count;

    public Book(String writer, String name, int count) {
        this.writer = writer;
        this.name = name;
        this.count = count;
    }

    public String getWriter() {
        return writer;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public void decrementCount() {
        count--;
    }
}