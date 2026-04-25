package B1;

import java.util.*;

public class Test {
    private static final Set<String> generatedIds = new HashSet<>();
    private static final List<Book> list = new ArrayList<>();
    private static final List<User> users = new ArrayList<>();
    private static final Map<String, List<String>> reservationsByPhone = new HashMap<>();

    public static void main(String[] args) {

        System.out.println("Выберите вариант:");
        System.out.println("1. Отобразить коллекцию книг");
        System.out.println("2. Добавить книгу в коллецию");
        System.out.println("3. Отобразить клиентов");
        System.out.println("4. Добавить клиента");
        System.out.println("5. Взять книгу из коллекции");
        System.out.println("6. Вернуть книгу в коллекцию");
        System.out.println("7. Выйти");

        Book book1 = new Book("Мастер и Маргарита", new Author("Михаил Булгаков"), generateId(), 3);
        Book book2 = new Book("Идиот", new Author("Фёдер Достоевский"), generateId(), 2);
        Book book3 = new Book("Маленький принц", new Author("Антуан де Сент-Экзюпери"), generateId(), 4);
        Book book4 = new Book("Гарри Поттер", new Author("Джоан Роулинг"), generateId(), 5);
        list.add(book1);
        list.add(book2);
        list.add(book3);
        list.add(book4);

        User user1 = new User("Вася", "Пупкин", "1111111");
        User user2 = new User("Антон", "Мартынов", "2222222");
        User user3 = new User("Матвей", "Чижик", "3333333");
        User user4 = new User("Павел", "Деревянко", "4444444");
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);

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
                    reserveBook(console);
                }
                case "6" -> {
                    returnBook(console);
                }
                case "7" -> {
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
        System.out.print("Количество экземпляров: ");
        int copies = Integer.parseInt(console.nextLine());
        Book book = new Book(bookname, author, generateId(), copies);
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

    private static void reserveBook(Scanner console) {
        printBookList(list);
        System.out.println("Выберите книгу (введите серийный номер):");
        String bookId = console.nextLine();

        Book selectedBook = findBookById(bookId);

        if (selectedBook == null) {
            System.out.println("Книга с таким серийным номером не найдена!");
            return;
        }

        if (!selectedBook.isAvailable()) {
            System.out.println("Все экземпляры этой книги уже забронированы!");
            return;
        }

        printUserList(users);
        System.out.println("Кто берет книгу? (введите номер телефона клиента):");
        String userPhone = console.nextLine();

        User selectedUser = findUserByPhone(userPhone);

        if (selectedUser == null) {
            System.out.println("Клиент с таким номером телефона не найден!");
            return;
        }

        selectedBook.borrowCopy();

        reservationsByPhone.computeIfAbsent(userPhone, k -> new ArrayList<>()).add(bookId);

        System.out.println("Книга '" + selectedBook.getName() + "' успешно забронирована для " +
                selectedUser.getName() + " " + selectedUser.getSurname() + " (осталось экземпляров: "
                    + selectedBook.getAvailableCopies() + "/" + selectedBook.getTotalCopies() + ")");
    }

    private static void returnBook(Scanner console) {
        printUserList(users);
        System.out.println("Введите номер телефона клиента, который возвращает книгу: ");
        String userPhone = console.nextLine();

        List<String> userBooks = reservationsByPhone.get(userPhone);
        if (userBooks == null || userBooks.isEmpty()) {
            System.out.println("У клиента с таким номером телефона нет забронированных книг!");
            return;
        }

        System.out.println("Книги, забронированные пользователем: ");
        for (String bookId : userBooks) {
            Book book = findBookById(bookId);
            if (book != null) {
                System.out.println("  - " + book.getName() + " (ID: " + bookId + ")");
            }
        }

        System.out.println("Введите ID книги, которую нужно вернуть: ");
        String bookId = console.nextLine();

        if (!userBooks.remove(bookId)) {
            System.out.println("У пользователя нет книги с таким ID!");
            return;
        }

        if (userBooks.isEmpty()) {
            reservationsByPhone.remove(userPhone);
        }

        Book returnedBook = findBookById(bookId);
        if (returnedBook != null) {
            returnedBook.returnCopy();
        }

        System.out.println("Книга успешно возвращена!");

    }

    private static Book findBookById(String bookId) {
        for (Book book : list) {
            if (book.getId().equals(bookId)) {
                return book;
            }
        }
        return null;
    }

    private static User findUserByPhone(String phone) {
        for (User user : users) {
            if (user.getPhonenumber().equals(phone)) {
                return user;
            }
        }
        return null;
    }

}
