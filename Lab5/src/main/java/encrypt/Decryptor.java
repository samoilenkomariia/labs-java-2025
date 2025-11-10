package encrypt;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

public class Decryptor extends FilterReader {
    private final int key;

    public Decryptor(Reader source, char key) {
        super(source);
        this.key = key;
    }

    @Override
    public int read() throws IOException {
        int rawChar = super.read();
        if (rawChar == -1) return -1;
        return rawChar - this.key;
    }
}
