package maow.javasdf.io.conversion;

import maow.javasdf.document.Document;

import java.io.IOException;

public interface ConvertedWriter {
    void write(Document document) throws IOException;
}
