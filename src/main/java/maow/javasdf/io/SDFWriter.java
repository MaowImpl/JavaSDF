package maow.javasdf.io;

import maow.javasdf.attribute.*;
import maow.javasdf.document.Document;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;

public class SDFWriter {
    private final BufferedWriter writer;

    public SDFWriter(Writer out) {
        this.writer = new BufferedWriter(out);
    }

    public void writeDocument(Document document) throws IOException {
        writer.write(document.getName());
        writer.newLine();
        for (InnerAttribute rootInnerAttribute : document.getRootInnerAttributes()) {
            writer.write(rootInnerAttribute.getName() + " : " + rootInnerAttribute.getValue());
            writer.newLine();
        }
        writer.write('-');
        writer.newLine();
        for (Attribute attribute : document.getAttributes()) {
            if (attribute instanceof AbstractAttribute) {
                writer.write(attribute.getName() + " : " + attribute.getValue());
                writer.newLine();
                for (InnerAttribute innerAttribute : ((AbstractAttribute) attribute).getInnerAttributes()) {
                    writer.write(innerAttribute.getName() + " : " + innerAttribute.getValue());
                    writer.newLine();
                }
                if (attribute instanceof CategoryAttribute) {
                    for (NestedAttribute nestedAttribute : ((CategoryAttribute) attribute).getNestedAttributes()) {
                        writeNestedAttribute(nestedAttribute);
                    }
                }
            }
        }
        writer.flush();
    }

    private void writeNestedAttribute(NestedAttribute nestedAttribute) throws IOException {
        writer.write(nestedAttribute.getName() + " : " + nestedAttribute.getValue());
        writer.newLine();
        for (InnerAttribute innerAttribute : nestedAttribute.getInnerAttributes()) {
            writer.write(innerAttribute.getName() + " : " + innerAttribute.getValue());
            writer.newLine();
        }
        for (NestedAttribute nestedAttribute1 : nestedAttribute.getNestedAttributes()) {
            writeNestedAttribute(nestedAttribute1);
        }
    }

    public void close() throws IOException {
        writer.close();
    }
}
