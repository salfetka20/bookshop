package B1;

import java.util.*;

public class Test {
    private static final Set<String> generatedIds = new HashSet<>();
    private static final List<Book> list = new ArrayList<>();
    private static final List<User> users = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println("Выберите вариант:");
        System.out.println("1. Отобразить коллекцию книг");
        System.out.println("2. Добавить книгу в коллецию");
        System.out.println("3. Отобразить клиентов");
        System.out.println("4. Добавить клиента");
        System.out.println("5. Выйти");

        Book book1 = new Book("Мастер и Маргарита", new Author("Михаил Булгаков"), generateId());
        Book book2 = new Book("Идиот", new Author("Фёдер Достоевский"), generateId());
        list.add(book1);
        list.add(book2);

        User user1 = new User("Вася", "Пупкин", "1111111");
        User user2 = new User("Антон", "Мартынов", "2222222");
        users.add(user1);
        users.add(user2);

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
                    printUserList(users);
                }
                case "4" -> {
                    addUser(console);
                    printUserList(users);
                }
                case "5" -> {
                    System.exit(0);
                }
                default -> {
                    System.out.println("Нет такого варианта");
                }
            }

        }

    }

    private static void addBook(Scanner console) {
        System.out.println("Введите данные книги");
        System.out.print("Название:");
        String bookname = console.nextLine();
        System.out.print("Автор:");
        Author author = new Author(console.nextLine());
        Book book = new Book(bookname, author, generateId());
        list.add(book);
    }

    private static String generateId() {
        String uuid = UUID.randomUUID().toString();
        while (generatedIds.contains(uuid)) {
            uuid = UUID.randomUUID().toString();
        }
        generatedIds.add(uuid);
        return uuid;
    }

    private static void printBookList(List<Book> list) {
        System.out.println("Текущий список книг: ");
        list.forEach(System.out::println);
    }

    private static void addUser(Scanner console) {
        System.out.println("Введите данные клиента");
        System.out.print("Имя:");
        String username = console.nextLine();
        System.out.print("Фамилия:");
        String usersurname = console.nextLine();
        System.out.print("Номер телефона:");
        String userphone = console.nextLine();
        User user = new User(username, usersurname, userphone);
        users.add(user);
    }

    private static void printUserList(List<User> users) {
        System.out.println("Текущий список клиентов: ");
        users.forEach(System.out::println);
    }
}
