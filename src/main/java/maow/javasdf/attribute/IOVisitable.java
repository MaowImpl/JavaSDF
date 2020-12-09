package maow.javasdf.attribute;

import maow.javasdf.io.SDFReader;
import maow.javasdf.io.SDFWriter;

import java.io.IOException;

public interface IOVisitable {
    void write(SDFWriter writer) throws IOException;
    void read(SDFReader reader) throws IOException;
}
