package maow.javasdf.attribute;

import maow.javasdf.io.SDFReader;
import maow.javasdf.io.SDFWriter;

import java.io.IOException;
import java.util.List;

public interface Attribute {
    String getName();
    String getValue();
    void setValue(String value);

    void write(SDFWriter writer) throws IOException;
    void read(SDFReader reader, List<Attribute> attributes) throws IOException;
}
