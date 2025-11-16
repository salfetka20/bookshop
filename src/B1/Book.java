package B1;

public class Book {

    private String name;
    private Author author;
    private String id;
    private boolean isReserved;
    private String reservedBy;

    public Book(String name, Author author, String id) {
        this.name = name;
        this.author = author;
        this.id = id;
        this.isReserved = false;
        this.reservedBy = null;
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

    public String getId() {
        return id;
    }

    public boolean isReserved() { return isReserved; }

    public void setReserved(boolean reserved) { isReserved = reserved; }

    public String getReservedBy() { return reservedBy; }

    public void setReservedBy(String reservedBy) { this.reservedBy = reservedBy; }


    @Override
    public String toString() {
        String status = isReserved ? " (Зарезервирована)" : " (Доступна)";
        return "Название книги: " + name + " | " + "Автор: " + author + " | " + "Серийный номер: " + id + status;
    }

}

