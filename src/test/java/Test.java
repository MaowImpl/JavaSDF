import maow.javasdf.attribute.*;
import maow.javasdf.document.Document;
import maow.javasdf.io.SDFReader;
import maow.javasdf.io.SDFWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

public class Test {
    public static void main(String[] args) throws IOException {
        final SDFReader sr = new SDFReader(new FileReader("Test.sdf"));
        final Document sdf = sr.getDocument();

        for (Attribute attribute : sdf.getAttributes()) {
            if (attribute instanceof AbstractAttribute) {
                System.out.println("Attribute - " + attribute.getName() + " : " + attribute.getValue());
                for (InnerAttribute inner : ((AbstractAttribute) attribute).getInnerAttributes()) {
                    System.out.println("Inner-Attribute - " + inner.getName() + " : " + inner.getValue());
                }
            }
            if (attribute instanceof CategoryAttribute) {
                for (NestedAttribute nested : ((CategoryAttribute) attribute).getNestedAttributes()) {
                    System.out.println("Nested Attribute - " + nested.getName() + " : " + nested.getValue());
                    for (InnerAttribute nestedInner : nested.getInnerAttributes()) {
                        System.out.println("Nested Inner-Attribute - " + nestedInner.getName() + " : " + nestedInner.getValue());
                    }
                    Collection<NestedAttribute> nestedAttributes = nested.getNestedAttributes();
                    while (nestedAttributes.size() > 0) {
                        for (NestedAttribute nested2 : nestedAttributes) {
                            System.out.println("Nested-Nested Attribute - " + nested2.getName() + " : " + nested2.getValue());
                            nestedAttributes = nested2.getNestedAttributes();
                            for (InnerAttribute inner2 : nested2.getInnerAttributes()) {
                                System.out.println("Nested-Nested Inner-Attribute - " + inner2.getName() + " : " + inner2.getValue());
                            }
                        }
                    }
                }
            }
        }

        final SDFWriter sw = new SDFWriter(new FileWriter("TestWritten.sdf"));
        sw.writeDocument(sdf);
        sw.flush();
        sw.close();
    }
}
