package B1;

public class Book {

    private String name;
    private Author author;
    private String id;
    private int totalCopies;
    private int availableCopies;

    public Book(String name, Author author, String id, int totalCopies) {
        this.name = name;
        this.author = author;
        this.id = id;
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public boolean isAvailable() {
        return availableCopies > 0;
    }

    public void borrowCopy() {
        if (availableCopies > 0) {
            availableCopies--;
        }
    }

    public void returnCopy() {
        if (availableCopies < totalCopies) {
            availableCopies++;
        }
    }


    @Override
    public String toString() {
        return "Название книги: " + name + " | " + "Автор: " + author + " | " + "Серийный номер: " + id + " | " + "Доступно: " + availableCopies + "/" + totalCopies;
    }

}

