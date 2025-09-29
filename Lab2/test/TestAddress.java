import org.junit.jupiter.api.Test;

public class TestAddress {

    @Test
    void testCapitalize() {
        Address addr = new Address();
        addr.setRegion("київська обл.");
        assert addr.toString().contains("Київська обл.");

        addr.setDistrict("броварський р-н");
        assert addr.toString().contains("Броварський р-н");

        addr.setLocality("м. бровари");
        assert addr.toString().contains("м. Бровари");

        addr.setStreet("вул. грушевського");
        assert addr.toString().contains("вул. Грушевського");

        addr.setStreet("вул. степана бандери");
        assert addr.toString().contains("вул. Степана Бандери");

        addr.setRegion("івано-франківська обл.");
        assert addr.toString().contains("Івано-Франківська обл.");
    }

    @Test
    void testToStringWithApartment() {
        Address addr = new Address();
        addr.setRegion("Київська обл.");
        addr.setDistrict("Броварський р-н");
        addr.setLocality("м. Бровари");
        addr.setStreet("вул. Грушевського");
        addr.setHouse("буд. 15");
        addr.setApartment("кв. 23");

        String expected = "Київська обл., Броварський р-н, м. Бровари, вул. Грушевського, буд. 15, кв. 23";
        assert addr.toString().equals(expected);
    }

    @Test
    void testToStringWithoutApartment() {
        Address addr = new Address();
        addr.setRegion("Львівська обл.");
        addr.setDistrict("Жовківський р-н");
        addr.setLocality("с. Малехів");
        addr.setStreet("вул. Центральна");
        addr.setHouse("буд. 7");

        String expected = "Львівська обл., Жовківський р-н, с. Малехів, вул. Центральна, буд. 7";
        assert addr.toString().equals(expected);
    }
}
