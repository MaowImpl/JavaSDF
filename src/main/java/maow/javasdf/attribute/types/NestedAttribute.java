package maow.javasdf.attribute.types;

import maow.javasdf.attribute.ParentAttribute;
import maow.javasdf.io.SDFReader;
import maow.javasdf.io.SDFWriter;

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
    public void read(SDFReader reader) {
        reader.addNestedAttribute(this);
    }
}
