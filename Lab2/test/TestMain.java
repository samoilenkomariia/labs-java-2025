import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

public class TestMain {

    private class InputSupplier implements Supplier<String>{
        private final String[] inputs;
        private int index = 0;

        public InputSupplier(String... inputs) {
            this.inputs = inputs;
        }

        @Override
        public String get() {
            if (index < inputs.length) {
                return inputs[index++];
            }
            return "3";
        }
    }

    @Test
    void testCreateRecord() {
        Supplier<String> mockInput = new InputSupplier("Іванов",
                "Іван",
                "20.01.1995",
                "380987490101",
                "Київська обл.",
                "Броварський р-н",
                "м. Бровари",
                "вул. Грушевського",
                "буд. 18Б",
                "");
        JournalRecord jr = Main.createRecord(mockInput);
        assert jr != null;
        assert jr.getLastName().equals("Іванов");
        assert jr.getFirstName().equals("Іван");
        assert jr.getDob().equals("20.01.1995");
        assert jr.getPhoneNum().equals("380987490101");
        assert jr.getAddress().toString().equals("Київська обл., Броварський р-н, м. Бровари, вул. Грушевського, буд. 18Б");
        assert jr.toString().equals("Студент: Іванов Іван, дата народження: 20.01.1995, домашня адреса: Київська обл., Броварський р-н, м. Бровари, вул. Грушевського, буд. 18Б, контактний телефон: +380987490101");

        mockInput = new InputSupplier("Іванов",
                "Іван",
                "20.01.1995",
                "380987490101",
                "Київська обл.",
                "Броварський р-н",
                "м. Бровари",
                "вул. Грушевського",
                "буд. 18Б",
                "кв. 12");
        jr = Main.createRecord(mockInput);
        assert jr != null;
        assert jr.getAddress().toString().equals("Київська обл., Броварський р-н, м. Бровари, вул. Грушевського, буд. 18Б, кв. 12");
        assert jr.toString().equals("Студент: Іванов Іван, дата народження: 20.01.1995, домашня адреса: Київська обл., Броварський р-н, м. Бровари, вул. Грушевського, буд. 18Б, кв. 12, контактний телефон: +380987490101");

        mockInput = new InputSupplier("Іванов",
                "Іван",
                "20.01.1995",
                "380987490101",
                "Київська обл.",
                "Броварський р-н",
                "3", // cancellation
                "3");
        jr = Main.createRecord(mockInput);
        assert jr == null;
    }

}
