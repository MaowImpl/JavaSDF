package maow.javasdf.attribute.types;

import maow.javasdf.attribute.AbstractAttribute;
import maow.javasdf.attribute.Attribute;
import maow.javasdf.io.SDFReader;
import maow.javasdf.io.SDFWriter;

import java.io.IOException;
import java.util.List;

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
    public void read(SDFReader reader, List<Attribute> attributes) {
        reader.resetNestedTree();
        reader.setActiveAttribute(this);
        attributes.add(this);
    }
}
