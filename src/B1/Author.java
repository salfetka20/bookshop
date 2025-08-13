package B1;

public class Author {

    private String name;

    public Author() {
    }

    public Author(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
