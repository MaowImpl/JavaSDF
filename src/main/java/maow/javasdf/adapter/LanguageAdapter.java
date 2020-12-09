package maow.javasdf.adapter;

import maow.javasdf.document.Document;

public interface LanguageAdapter<T> {
    T convert(Document document);
}
