package B1;

import java.util.*;

public class Test {
    private static final Set<String> generatedIds = new HashSet<>();
    private static final List<Book> list = new ArrayList();
    public static void main(String[] args) {

        System.out.println("Выберите вариант:");
        System.out.println("1. Отобразить коллекцию книг");
        System.out.println("2. Добавить книгу в коллецию");
        System.out.println("3. Выйти");

        Book book1 = new Book("Мастер и Маргарита", new Author("Михаил Булгаков"), generateId());
        Book book2 = new Book("Идиот", new Author("Фёдер Достоевский"), generateId());
        list.add(book1);
        list.add(book2);

        Scanner console = new Scanner(System.in);

        while (true) {
            String num = console.nextLine();
            switch (num) {
                case "1" -> {
                    printBookList(list);
                }
                case "2" -> {
                    addBook(console);
                    printBookList(list);
                }
                case "3" -> {
                    System.exit(0);
                }
                default -> {
                    System.out.println("Нет такого варианта");
                }
            }

        }

    }
    private static void addBook(Scanner console){
        System.out.print("Введите название книги и автора:");
        String bookname = console.nextLine();
        Author author = new Author(console.nextLine());
        Book book = new Book(bookname, author, generateId());
        list.add(book);
    }

    private static String generateId(){
        String uuid = UUID.randomUUID().toString();
        while (generatedIds.contains(uuid)){
            uuid = UUID.randomUUID().toString();
        }
        generatedIds.add(uuid);
        return uuid;
    }

    private static void printBookList(List<Book> list){
        System.out.println("Текущий список книг: ");
        list.forEach(System.out::println);
    }
}
