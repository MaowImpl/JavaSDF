package maow.javasdf.attribute.types;

import maow.javasdf.attribute.Attribute;
import maow.javasdf.attribute.ParentAttribute;
import maow.javasdf.io.SDFReader;
import maow.javasdf.io.SDFWriter;

import java.util.List;

public class NestedAttribute extends ParentAttribute {
    public NestedAttribute(String name, String value) {
        super(name, value);
    }

    public NestedAttribute(String name) {
        super(name);
    }

    @Override
    public void write(SDFWriter writer) {}

    @Override
    public void read(SDFReader reader, List<Attribute> attributes) {
        reader.addNestedAttribute(this);
    }
}
