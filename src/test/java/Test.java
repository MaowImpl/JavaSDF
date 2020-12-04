import maow.javasdf.attribute.*;
import maow.javasdf.document.Document;
import maow.javasdf.io.SDFReader;
import maow.javasdf.io.SDFWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        final SDFReader sr = new SDFReader(new FileReader("Test.sdf"));
        final Document sdf = sr.getDocument();
        sr.close();

        for (Attribute attribute : sdf.getAttributes()) {
            if (attribute instanceof CategoryAttribute) {
                for (NestedAttribute nestedAttribute : ((CategoryAttribute) attribute).getNestedAttributes()) {
                    System.out.println(nestedAttribute.getName());
                    for (InnerAttribute innerAttribute : nestedAttribute.getInnerAttributes()) {
                        System.out.println(innerAttribute.getName());
                    }
                    for (NestedAttribute nestedAttribute1 : nestedAttribute.getNestedAttributes()) {
                        System.out.println(nestedAttribute1.getName());
                        for (InnerAttribute innerAttribute1 : nestedAttribute1.getInnerAttributes()) {
                            System.out.println(innerAttribute1.getName());
                        }
                        for (NestedAttribute nestedAttribute2 : nestedAttribute1.getNestedAttributes()) {
                            System.out.println(nestedAttribute2.getName());
                            for (InnerAttribute innerAttribute2 : nestedAttribute2.getInnerAttributes()) {
                                System.out.println(innerAttribute2.getName());
                            }
                        }
                    }
                }
            }
        }

        final SDFWriter sw = new SDFWriter(new FileWriter("TestWritten.sdf"));
        sw.writeDocument(sdf);
        sw.close();
    }
}
