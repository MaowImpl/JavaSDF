package maow.javasdf.attribute.types;

import maow.javasdf.attribute.AbstractAttribute;
import maow.javasdf.io.SDFReader;
import maow.javasdf.io.SDFWriter;

import java.io.IOException;

public class BasicAttribute extends AbstractAttribute {
    public BasicAttribute(String name) {
        super(name);
    }

    public BasicAttribute(String name, String value) {
        super(name, value);
    }

    @Override
    public void write(SDFWriter writer) throws IOException {
        writer.writeAttribute(this);
    }

    @Override
    public void read(SDFReader reader) {
        reader.resetNestedTree();
        reader.setActiveAttribute(this);
        reader.addAttribute(this);
    }
}
