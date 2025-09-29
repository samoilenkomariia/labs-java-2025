import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        List<JournalRecord> journal = new ArrayList<>();
        String MENU = """
                1 - Додати запис в журнал куратора
                2 - Переглянути записи в журналі куратора
                3 - Вихід
                """;
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.print(MENU);
            switch (scanner.nextLine()) {
                case "1"-> {
                    JournalRecord rec = createRecord(scanner::nextLine);
                    if (rec != null) {
                        journal.add(rec);
                    } else {
                        System.out.println("Запис не було збережено, операцію скасовано");
                    }
                }
                case "2" -> {
                    System.out.println("Записи в журналі куратора:");
                    for (var r : journal) {
                        System.out.println(r);
                    }
                }
                default -> System.exit(0);
            }
        }
    }

    public static JournalRecord createRecord(Supplier<String> inputSupplier) {
        JournalRecord record = new JournalRecord();
        Address address = new Address();
        Map<String, Boolean> data = new HashMap<>(Map.of(
            "lastname", false,
            "firstname", false,
            "dob", false,
            "phoneNum", false,
            "region", false,
            "district", false,
            "locality", false,
            "street", false,
            "house", false,
            "apartment", false
        ));
        while (true) {
            if (!data.get("lastname")) {
                System.out.println("Введіть прізвище у форматі Іванов / Коваленк о-Петренко:");
                String input = inputSupplier.get().trim();
                if (input.equals("3")) {
                    return null;
                }
                if (DataVerifier.isValidName(input)) {
                    record.setLastName(input);
                    data.put("lastname", true);
                } else {
                    System.out.println("Некоректне прізвище!");
                    continue;
                }
            }
            if (!data.get("firstname")) {
                System.out.println("Введіть ім'я у форматі Іван / Петро:");
                String input = inputSupplier.get().trim();
                if (input.equals("3")) {
                    return null;
                }
                if (DataVerifier.isValidName(input)) {
                    record.setFirstName(input);
                    data.put("firstname", true);
                } else {
                    System.out.println("Некоректне ім'я!");
                    continue;
                }
            }
            if (!data.get("dob")) {
                System.out.println("Веддіть дату народження у форматі 01.01.2000:");
                String input = inputSupplier.get().trim();
                if (input.equals("3")) {
                    return null;
                }
                if (DataVerifier.isValidDate(input)) {
                    record.setDob(LocalDate.parse(input, DateTimeFormatter.ofPattern("dd.MM.yyyy")));
                    data.put("dob", true);
                } else {
                    System.out.println("Некоректна дата");
                    continue;
                }
            }
            if (!data.get("phoneNum")) {
                System.out.println("Введіть номер телефону у форматі 380XXXXXXXXX:");
                String input = inputSupplier.get().trim();
                if (input.equals("3")) {
                    return null;
                }
                if (DataVerifier.isValidPhoneNum(input)) {
                    record.setPhoneNum(input);
                    data.put("phoneNum", true);
                } else {
                    System.out.println("Некоректний номер телефону!");
                    continue;
                }
            }
            if (!data.get("region")) {
                System.out.println("Введіть область у форматі Київська обл.:");
                String input = inputSupplier.get().trim();
                if (input.equals("3")) {
                    return null;
                }
                if (DataVerifier.isValidRegion(input)) {
                    data.put("region", true);
                    address.setRegion(input);
                } else {
                    System.out.println("Некоректно введені дані");
                    continue;
                }
            }
            if (!data.get("district")) {
                System.out.println("Введіть район у форматі Обухівський р-н:");
                String input = inputSupplier.get().trim();
                if (input.equals("3")) {
                    return null;
                }
                if (DataVerifier.isValidDistrict(input)) {
                    data.put("district", true);
                    address.setDistrict(input);
                } else {
                    System.out.println("Некоректно введені дані!");
                    continue;
                }
            }
            if (!data.get("locality")) {
                System.out.println("Введіть населений пункт у форматі м. Київ / с. Петропавлівська Борщагівка:");
                String input = inputSupplier.get().trim();
                if (input.equals("3")) {
                    return null;
                }
                if (DataVerifier.isValidLocality(input)) {
                    data.put("locality", true);
                    address.setLocality(input);
                } else {
                    System.out.println("Некоректно введені дані!");
                    continue;
                }
            }
            if (!data.get("street")) {
                System.out.println("Введіть вулицю у форматі вул. Шевченка / просп. Перемоги:");
                String input = inputSupplier.get().trim();
                if (input.equals("3")) {
                    return null;
                }
                if (DataVerifier.isValidStreet(input)) {
                    data.put("street", true);
                    address.setStreet(input);
                } else {
                    System.out.println("Некоректно введені дані!");
                    continue;
                }
            }
            if (!data.get("house")) {
                System.out.println("Введіть будинок у форматі буд. 1 / буд. 12-А:");
                String input = inputSupplier.get().trim();
                if (input.equals("3")) {
                    return null;
                }
                if (DataVerifier.isValidHouse(input)) {
                    data.put("house", true);
                    address.setHouse(input);
                } else {
                    System.out.println("Некоректно введені дані!");
                    continue;
                }
            }
            if (!data.get("apartment")) {
                System.out.println("Введіть квартиру у форматі кв. 1 / кв. 12 (або пропустіть цей крок, натиснувши Enter):");
                String input = inputSupplier.get();
                if (input.equals("3")) {
                    return null;
                }
                if (input.isEmpty()) {
                    data.put("apartment", true);
                    address.setApartment(null);
                } else if (DataVerifier.isValidApartment(input)) {
                    data.put("apartment", true);
                    address.setApartment(input);
                } else {
                    System.out.println("Некоректно введені дані!");
                    continue;
                }
            }
            break;
        }
        record.setAddress(address);
        return record;
    }
}
