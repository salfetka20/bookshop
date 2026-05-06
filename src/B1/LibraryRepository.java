package B1;

import java.util.*;

public class LibraryRepository {
    private final List<Book> books = new ArrayList<>();
    private final List<User> users = new ArrayList<>();
    private final Map<String, List<String>> reservations = new HashMap<>();
    private final Set<String> generatedIds = new HashSet<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    public Book findBookById(String id) {
        for (Book book : books) {
            if (book.getId().equals(id)) {
                return book;
            }
        }
        return null;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    public User findUserByPhone(String phone) {
        for (User user : users) {
            if (user.getPhonenumber().equals(phone)) {
                return user;
            }
        }
        return null;
    }

    public List<String> getUserBooks(String phone) {
        return reservations.get(phone);
    }

    public void addReservation(String phone, String bookId) {
        reservations.computeIfAbsent(phone, k -> new ArrayList<>()).add(bookId);
    }

    public boolean removeReservation(String phone, String bookId) {
        List<String> userBooks = reservations.get(phone);
        if (userBooks == null) return false;
        boolean removed = userBooks.remove(bookId);
        if (userBooks.isEmpty()) {
            reservations.remove(phone);
        }
        return removed;
    }

    public String generateId() {
        String uuid;
        do {
            uuid = UUID.randomUUID().toString();
        } while (generatedIds.contains(uuid));
        generatedIds.add(uuid);
        return uuid;
    }
}
