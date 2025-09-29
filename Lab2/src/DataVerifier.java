import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class DataVerifier {

    private static final String NAME_PATTERN = "[А-Яа-яЇїІіЄєҐґ]+(-[А-Яа-яЇїІіЄєҐґ]+)*";

    private static final String PHONE_PATTERN = "380[0-9]{9}";

    private static final String[] REGION_PATTERNS = {
            "[А-Яа-яЇїІіЄєҐґ]+ обл\\.",  // Київська обл.
            "[А-Яа-яЇїІіЄєҐґ]+ область", // Чернівецька область
            "АР [А-Яа-яЇїІіЄєҐґ]+"             // АР Крим
    };

    private static final String[] STREET_PATTERNS = {
            "(вул\\.|вулиця) [А-Яа-яЇїІіЄєҐґ]+([ -][А-Яа-яЇїІіЄєҐґ]+)*",   // вул. Хрещатик, вул. Івана Франка
            "просп\\. [А-Яа-яЇїІіЄєҐґ]+([ -][А-Яа-яЇїІіЄєҐґ]+)*", // просп. Перемоги
            "пров\\. [А-Яа-яЇїІіЄєҐґ]+([ -][А-Яа-яЇїІіЄєҐґ]+)*",  // пров. Шевченка
            "бул\\. [А-Яа-яЇїІіЄєҐґ]+([ -][А-Яа-яЇїІіЄєҐґ]+)*",   // бул. Лесі Українки
            "шосе [А-Яа-яЇїІіЄєҐґ]+([ -][А-Яа-яЇїІіЄєҐґ]+)*",     // шосе Київське
            "пл\\. [А-Яа-яЇїІіЄєҐґ]+([ -][А-Яа-яЇїІіЄєҐґ]+)*",     // пл. Ринок
            "майдан [А-Яа-яЇїІіЄєҐґ]+([ -][А-Яа-яЇїІіЄєҐґ]+)*"
    };

    private static final String HOUSE_PATTERN = "буд\\. [0-9]+([ -]?[А-Яа-яЇїІіЄєҐґ]+)?(/[0-9]+)?";

    private static final String APARTMENT_PATTERN = "кв\\. [0-9]+(-?[А-Яа-яЇїІіЄєҐґ]+)?(/[0-9]+)?";

    private static final String DISTRICT_PATTERN = "[А-Яа-яЇїІіЄєҐґ]+(-[А-Яа-яЇїІіЄєҐґ]+)* (р-н|район)";

    private static final String[] LOCALITY_PATTERNS = {
            "м\\. [А-Яа-яЇїІіЄєҐґ]+([ -][А-Яа-яЇїІіЄєҐґ]+)*",      // м. Київ
            "смт\\. [А-Яа-яЇїІіЄєҐґ]+([ -][А-Яа-яЇїІіЄєҐґ]+)*",    // смт. Ворзель
            "с\\. [А-Яа-яЇїІіЄєҐґ]+([ -][А-Яа-яЇїІіЄєҐґ]+)*",      // с. Лісники
            "с-ще [А-Яа-яЇїІіЄєҐґ]+([ -][А-Яа-яЇїІіЄєҐґ]+)*"       // с-ще Коцюбинське
    };

    public static boolean isValidName(String name) {

        if (name == null || name.isEmpty()) {
            return false;
        }

        return name.matches(NAME_PATTERN);
    }

    public static boolean isValidPhoneNum(String phoneNum) {

        if (phoneNum == null || phoneNum.isEmpty()) {
            return false;
        }

        return phoneNum.matches(PHONE_PATTERN);
    }

    private static boolean isValidData(String input, String... patterns) {

        if (input == null || input.isEmpty()) {
            return false;
        }

        String[] parts = input.split(" ", 2);
        if (parts.length != 2) {
            return false;
        }

        for (var regex : patterns) {
            if (input.matches(regex)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValidRegion(String input) {
        return isValidData(input, REGION_PATTERNS);
    } // region

    public static boolean isValidDistrict(String district) {
        return isValidData(district, DISTRICT_PATTERN);
    } // district

    public static boolean isValidLocality(String locality) {
        return isValidData(locality, LOCALITY_PATTERNS);
    } // city/village

    public static boolean isValidStreet(String street) {
        return isValidData(street, STREET_PATTERNS);
    } // street

    public static boolean isValidHouse(String house) {
        return isValidData(house, HOUSE_PATTERN);
    } // house

    public static boolean isValidApartment(String apartment) {
        return isValidData(apartment, APARTMENT_PATTERN);
    } // apartment

    public static boolean isValidDate(String date) {
        if (date == null || date.isEmpty()) {
            return false;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.uuuu").withResolverStyle(ResolverStyle.STRICT);
        try {
            LocalDate.parse(date, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
