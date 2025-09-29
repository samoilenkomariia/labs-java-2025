import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TestJournalRecord {

    @Test
    void testCapitalize() {
        JournalRecord jr = new JournalRecord();
        jr.setLastName("петренко");
        assert jr.getLastName().equals("Петренко");

        jr.setFirstName("марія-анна");
        assert jr.getFirstName().equals("Марія-Анна");

        jr.setLastName(" осінчук-шеремета ");
        assert jr.getLastName().equals("Осінчук-Шеремета");

        jr.setLastName("луЦько");
        assert jr.getLastName().equals("Луцько");

        jr.setLastName("  ковАленко-пЕтренко  ");
        assert jr.getLastName().equals("Коваленко-Петренко");
    }

    @Test
    void testToString() {
        JournalRecord jr = new JournalRecord();
        Address addr = new Address();
        addr.setRegion("Київська обл.");
        addr.setDistrict("Броварський р-н");
        addr.setLocality("м. Бровари");
        addr.setStreet("вул. Грушевського");
        addr.setHouse("буд. 15");
        addr.setApartment("кв. 23");
        jr.setAddress(addr);
        jr.setLastName("Іванов");
        jr.setFirstName("Іван");
        jr.setDob(LocalDate.parse("15.01.2000", DateTimeFormatter.ofPattern("dd.MM.uuuu")));
        jr.setPhoneNum("380987490101");

        String expected = "Студент: Іванов Іван, дата народження: 15.01.2000, домашня адреса: Київська обл., Броварський р-н, м. Бровари, вул. Грушевського, буд. 15, кв. 23, контактний телефон: +380987490101";
        assert jr.toString().equals(expected);
    }
}
