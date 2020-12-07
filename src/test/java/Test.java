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

        final SDFWriter sw = new SDFWriter(new FileWriter("TestWritten.sdf"));
        sw.writeDocument(sdf);
        sw.flush();
        sw.close();
    }
}
