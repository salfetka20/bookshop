package B1;

public class Book {

    private String name;
    private Author author;
    private String id;

    public Book(String name, Author author, String id) {
        this.name = name;
        this.author = author;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author.toString();
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Название книги: " + name + " | " + "Автор: " + author + " | " + "Серийный номер: " + id;
    }

}

