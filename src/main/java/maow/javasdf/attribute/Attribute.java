package maow.javasdf.attribute;

public interface Attribute extends IOVisitable {
    String getName();
    String getValue();
    void setValue(String value);
}
