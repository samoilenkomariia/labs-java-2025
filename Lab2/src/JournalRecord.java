import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class JournalRecord {
    private String lastName;
    private String firstName;
    private LocalDate dob;
    private String phoneNum;
    private Address address;

    @Override
    public String toString() {
        return "Студент: %s %s, дата народження: %s, домашня адреса: %s, контактний телефон: +%s".formatted(lastName, firstName,
        dob.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")).toString(), address.toString(), phoneNum);
    }

    public void setLastName(String lastName) {
        this.lastName = capitalize(lastName);
    }

    public void setFirstName(String firstName) {
        this.firstName = capitalize(firstName);
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    private String capitalize(String s) {
        s = s.trim();
        s = s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
        if (s.contains("-")) {
            int hyphenIndex = s.indexOf("-");
            s = s.substring(0, hyphenIndex+1) +
                    s.substring(hyphenIndex+1, hyphenIndex+2).toUpperCase() +
                    s.substring(hyphenIndex+2);
        }
        return s;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getDob() {
        return dob.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")).toString();
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public Address getAddress() {
        return address;
    }
}
