import org.junit.jupiter.api.Test;

public class TestDataVerifier {

    @Test
    void testValidDates() {
        assert DataVerifier.isValidDate("15.08.1995");
        assert DataVerifier.isValidDate("15.08.2026");
        assert DataVerifier.isValidDate("29.02.2024"); // leap year
    }

    @Test
    void testInvalidDates() {
        assert !DataVerifier.isValidDate("32.01.2000");
        assert !DataVerifier.isValidDate("29.02.2023"); // not a leap year
        assert !DataVerifier.isValidDate("15.13.2000");
        assert !DataVerifier.isValidDate("15-08-2000");
        assert !DataVerifier.isValidDate("15/08/2000");
        assert !DataVerifier.isValidDate("15082000");
        assert !DataVerifier.isValidDate("abc");
        assert !DataVerifier.isValidDate("");
    }

    @Test
    void testValidNames() {
        assert DataVerifier.isValidName("Іван");
        assert DataVerifier.isValidName("Марія-Анна");
        assert DataVerifier.isValidName("Коваленко");
        assert DataVerifier.isValidName("Осінчук-Шевченко");
    }

    @Test
    void testInvalidNames() {
        assert !DataVerifier.isValidName("");
        assert !DataVerifier.isValidName("Іван123");
        assert !DataVerifier.isValidName("Марія Анна");
        assert !DataVerifier.isValidName("Коваленко_Шевченко");
        assert !DataVerifier.isValidName("John");
    }

    @Test
    void testValidPhoneNumbers() {
        assert DataVerifier.isValidPhoneNum("380987490101");
    }

    @Test
    void testInvalidPhoneNumbers() {
        assert !DataVerifier.isValidPhoneNum("");
        assert !DataVerifier.isValidPhoneNum("38098749010"); // too short
        assert !DataVerifier.isValidPhoneNum("3809874901011"); // too long
        assert !DataVerifier.isValidPhoneNum("123456789012"); // wrong prefix
        assert !DataVerifier.isValidPhoneNum("38098A490101"); // contains letter
        assert !DataVerifier.isValidPhoneNum("380 987490101"); // contains space
        assert !DataVerifier.isValidPhoneNum("+380987490101"); // contains plus
    }

    @Test
    void testValidRegions() {
        assert DataVerifier.isValidRegion("Київська обл.");
        assert DataVerifier.isValidRegion("Львівська область");
        assert DataVerifier.isValidRegion("АР Крим");
    }

    @Test
    void testInvalidRegions() {
        assert !DataVerifier.isValidRegion("");
        assert !DataVerifier.isValidRegion("Київська");
        assert !DataVerifier.isValidRegion("область Київська");
        assert !DataVerifier.isValidRegion("Львівська обл");
        assert !DataVerifier.isValidRegion("АРКрим");
        assert !DataVerifier.isValidRegion("Львівська область.");
    }

    @Test
    void testValidDistricts() {
        assert DataVerifier.isValidDistrict("Шевченківський р-н");
        assert DataVerifier.isValidDistrict("Голосіївський район");
    }

    @Test
    void testInvalidDistricts() {
        assert !DataVerifier.isValidDistrict("");
        assert !DataVerifier.isValidDistrict("Шевченківський");
        assert !DataVerifier.isValidDistrict("Шевченківський р-н.");
        assert !DataVerifier.isValidDistrict("р-н Шевченківський");
        assert !DataVerifier.isValidDistrict("Голосіївський район.");
    }

    @Test
    void testValidLocalities() {
        assert DataVerifier.isValidLocality("м. Київ");
        assert DataVerifier.isValidLocality("смт. Ворзель");
        assert DataVerifier.isValidLocality("с. Лісники");
        assert DataVerifier.isValidLocality("с. Софіївська Борщагівка");
        assert DataVerifier.isValidLocality("с-ще Яструбине");
    }

    @Test
    void testInvalidLocalities() {
        assert !DataVerifier.isValidLocality("");
        assert !DataVerifier.isValidLocality("Київ");
        assert !DataVerifier.isValidLocality("м Київ");
        assert !DataVerifier.isValidLocality("м. Київ.");
        assert !DataVerifier.isValidLocality("смт Ворзель");
        assert !DataVerifier.isValidLocality("с Лісники");
        assert !DataVerifier.isValidLocality("с-щеЯструбине");
    }

    @Test
    void testValidStreets() {
        assert DataVerifier.isValidStreet("вул. Лесі Українки");
        assert DataVerifier.isValidStreet("вулиця Олеся Гончара");
        assert DataVerifier.isValidStreet("просп. Перемоги");
        assert DataVerifier.isValidStreet("бул. Тараса Шевченка");
        assert DataVerifier.isValidStreet("пров. Козацький");
        assert DataVerifier.isValidStreet("шосе Бориспільське");
        assert DataVerifier.isValidStreet("майдан Незалежності");
    }

    @Test
    void testInvalidStreets() {
        assert !DataVerifier.isValidStreet("");
        assert !DataVerifier.isValidStreet("Лесі Українки");
        assert !DataVerifier.isValidStreet("вул Лесі Українки");
        assert !DataVerifier.isValidStreet("вулиця. Олеся Гончара");
        assert !DataVerifier.isValidStreet("вул.Лесі Українки");
        assert !DataVerifier.isValidStreet("вул. Лесі Українки.");
        assert !DataVerifier.isValidStreet("просп Перемоги");
        assert !DataVerifier.isValidStreet("бул Тараса Шевченка");
        assert !DataVerifier.isValidStreet("пров Козацький");
        assert !DataVerifier.isValidStreet("шосе Бориспільське.");
        assert !DataVerifier.isValidStreet("майданНезалежності");
    }

    @Test
    void testValidHouses() {
        assert DataVerifier.isValidHouse("буд. 12");
        assert DataVerifier.isValidHouse("буд. 12А");
        assert DataVerifier.isValidHouse("буд. 14АБ");
        assert DataVerifier.isValidHouse("буд. 12-А");
        assert DataVerifier.isValidHouse("буд. 12-АБ");
        assert DataVerifier.isValidHouse("буд. 12/1");
        assert DataVerifier.isValidHouse("буд. 12-Б/3");
        assert DataVerifier.isValidHouse("буд. 12-БВ/3");
    }

    @Test
    void testInvalidHouses() {
        assert !DataVerifier.isValidHouse("");
        assert !DataVerifier.isValidHouse("абс");
        assert !DataVerifier.isValidHouse("12");
        assert !DataVerifier.isValidHouse("буд 12");
        assert !DataVerifier.isValidHouse("буд.12");
        assert !DataVerifier.isValidHouse("буд. 12А Б");
        assert !DataVerifier.isValidHouse("буд. 12_А");
        assert !DataVerifier.isValidHouse("буд. 12-");
        assert !DataVerifier.isValidHouse("буд. /1");
    }

    @Test
    void testValidApartments() {
        assert DataVerifier.isValidApartment("кв. 34");
        assert DataVerifier.isValidApartment("кв. 56А");
        assert DataVerifier.isValidApartment("кв. 78-Б");
        assert DataVerifier.isValidApartment("кв. 90/1");
        assert DataVerifier.isValidApartment("кв. 12-В/3");
    }

    @Test
    void testInvalidApartments() {
        assert !DataVerifier.isValidApartment("");
        assert !DataVerifier.isValidApartment("абс");
        assert !DataVerifier.isValidApartment("34");
        assert !DataVerifier.isValidApartment("кв 34");
        assert !DataVerifier.isValidApartment("кв.34");
        assert !DataVerifier.isValidApartment("кв. 56 А");
        assert !DataVerifier.isValidApartment("кв. 78_Б");
        assert !DataVerifier.isValidApartment("кв. 90-");
        assert !DataVerifier.isValidApartment("кв. /1");
    }
}
