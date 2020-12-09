package maow.javasdf.io.conversion;

import maow.javasdf.document.Document;

import java.io.Writer;

public abstract class AbstractConvertedWriter implements ConvertedWriter {
    protected final Document document;
    protected final Writer out;

    public AbstractConvertedWriter(Document document, Writer out) {
        this.document = document;
        this.out = out;
    }
}
