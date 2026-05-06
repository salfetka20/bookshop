package B1;

public class LibraryService {
    private final LibraryRepository repository;

    public LibraryService(LibraryRepository repository) {
        this.repository = repository;
    }

    public void addBook(String name, String authorName, int copies) {
        Author author = new Author(authorName);
        Book book = new Book(name, author, repository.generateId(), copies);
        repository.addBook(book);
    }

    public void addUser(String name, String surname, String phone) {
        User user = new User(name, surname, phone);
        repository.addUser(user);
    }

    public String reserveBook(String bookId, String userPhone) {
        Book book = repository.findBookById(bookId);
        if (book == null) {
            return "Книга с таким ID не найдена!";
        }

        if (book.getAvailableCopies() <= 0) {
            return "Все экземпляры книги '" + book.getName() + "' уже забронированы!";
        }

        User user = repository.findUserByPhone(userPhone);
        if (user == null) {
            return "Пользователь с таким телефоном не найден!";
        }

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        repository.addReservation(userPhone, bookId);

        return "Книга '" + book.getName() + "' забронирована для " +
                user.getName() + " " + user.getSurname() +
                " (осталось: " + book.getAvailableCopies() + "/" + book.getTotalCopies() + ")";
    }

    public String returnBook(String userPhone, String bookId) {
        if (repository.getUserBooks(userPhone) == null ||
                repository.getUserBooks(userPhone).isEmpty()) {
            return "У пользователя нет забронированных книг!";
        }

        if (!repository.removeReservation(userPhone, bookId)) {
            return "У пользователя нет книги с таким ID!";
        }

        Book book = repository.findBookById(bookId);
        if (book != null) {
            if (book.getAvailableCopies() < book.getTotalCopies()) {
                book.setAvailableCopies(book.getAvailableCopies() + 1);
            }
        }
        return "Книга успешно возвращена!";
    }

    public LibraryRepository getRepository() {
        return repository;
    }
}

