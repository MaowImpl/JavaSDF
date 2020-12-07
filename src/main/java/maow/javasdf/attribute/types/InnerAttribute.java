package maow.javasdf.attribute.types;

import maow.javasdf.attribute.Attribute;
import maow.javasdf.io.SDFReader;
import maow.javasdf.io.SDFWriter;

import java.io.IOException;
import java.util.List;

public class InnerAttribute implements Attribute {
    private final String name;
    private String value;

    public InnerAttribute(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public InnerAttribute(String name) {
        this.name = name;
        this.value = "";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public void write(SDFWriter writer) throws IOException {
        writer.writeInnerAttribute(this);
    }

    @Override
    public void read(SDFReader reader, List<Attribute> attributes) {
        reader.addInnerAttribute(this);
    }
}
