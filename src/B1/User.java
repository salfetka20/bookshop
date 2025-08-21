package B1;

public class User {

    private String name;
    private String surname;
    private String phonenumber;

    public User(String name, String surname, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.phonenumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Имя: " + name + " | " + "Фамилия: " + surname + " | " + "Номер телефона: " + phonenumber;
    }
}
