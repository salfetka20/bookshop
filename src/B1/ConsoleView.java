package B1;

import java.util.Scanner;

public class ConsoleView {
    private final LibraryService service;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleView(LibraryService service) {
        this.service = service;
    }

    public void start() {

        service.addBook("Мастер и Маргарита", "Михаил Булгаков", 3);
        service.addBook("Идиот", "Фёдор Достоевский", 2);
        service.addUser("Вася", "Пупкин", "1111111");
        service.addUser("Антон", "Мартынов", "2222222");

        while (true) {
            printMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> showBooks();
                case "2" -> addBook();
                case "3" -> showUsers();
                case "4" -> addUser();
                case "5" -> reserveBook();
                case "6" -> returnBook();
                case "7" -> System.exit(0);
                default -> System.out.println("Нет такого варианта");
            }
        }
    }

    private void printMenu() {
        System.out.println("\nВыберите вариант:");
        System.out.println("1. Отобразить коллекцию книг");
        System.out.println("2. Добавить книгу в коллекцию");
        System.out.println("3. Отобразить клиентов");
        System.out.println("4. Добавить клиента");
        System.out.println("5. Взять книгу");
        System.out.println("6. Вернуть книгу");
        System.out.println("7. Выйти");
    }

    private void showBooks() {
        System.out.println("Текущий список книг:");
        service.getRepository().getAllBooks().forEach(System.out::println);
    }

    private void addBook() {
        System.out.print("Название: ");
        String name = scanner.nextLine();
        System.out.print("Автор: ");
        String author = scanner.nextLine();
        System.out.print("Количество экземпляров: ");
        int copies = Integer.parseInt(scanner.nextLine());
        service.addBook(name, author, copies);
        System.out.println("Книга добавлена!");
    }

    private void showUsers() {
        System.out.println("Текущий список клиентов:");
        service.getRepository().getAllUsers().forEach(System.out::println);
    }

    private void addUser() {
        System.out.print("Имя: ");
        String name = scanner.nextLine();
        System.out.print("Фамилия: ");
        String surname = scanner.nextLine();
        System.out.print("Номер телефона: ");
        String phone = scanner.nextLine();
        service.addUser(name, surname, phone);
        System.out.println("Клиент добавлен!");
    }

    private void reserveBook() {
        showBooks();
        System.out.print("Введите ID книги: ");
        String bookId = scanner.nextLine();
        showUsers();
        System.out.print("Введите телефон пользователя: ");
        String phone = scanner.nextLine();

        String result = service.reserveBook(bookId, phone);
        System.out.println(result);
    }

    private void returnBook() {
        showUsers();
        System.out.print("Введите телефон пользователя: ");
        String phone = scanner.nextLine();

        var userBooks = service.getRepository().getUserBooks(phone);
        if (userBooks != null && !userBooks.isEmpty()) {
            System.out.println("Книги пользователя:");
            userBooks.forEach(bookId -> {
                Book book = service.getRepository().findBookById(bookId);
                if (book != null) {
                    System.out.println("  - " + book.getName() + " (ID: " + bookId + ")");
                }
            });
        }

        System.out.print("Введите ID книги для возврата: ");
        String bookId = scanner.nextLine();

        String result = service.returnBook(phone, bookId);
        System.out.println(result);
    }
}
