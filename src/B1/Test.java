package B1;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        LibraryRepository repository = new LibraryRepository();
        LibraryService service = new LibraryService(repository);
        ConsoleView view = new ConsoleView(service);
        view.start();
    }
}