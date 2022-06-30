package co.ab180.abson;

import static co.ab180.abson.serialization.SerializerKt.serialize;

public final class Abson {

    static final boolean DEFAULT_SERIALIZE_NULLS = false;
    static final boolean DEFAULT_PRETTY_PRINT = false;
    static final boolean DEFAULT_ESCAPE_HTML = true;

    final boolean serializeNulls;
    final boolean prettyPrinting;
    final boolean htmlSafe;

    public Abson() {
        this(DEFAULT_SERIALIZE_NULLS,
                DEFAULT_PRETTY_PRINT,
                DEFAULT_ESCAPE_HTML);
    }

    Abson(boolean serializeNulls, boolean prettyPrinting, boolean htmlSafe) {
        this.serializeNulls = serializeNulls;
        this.prettyPrinting = prettyPrinting;
        this.htmlSafe = htmlSafe;
    }

    public String toJson(Object src) {
        // TODO implement the method toJson()
        return serialize(src);  // Temporary code...
    }

    public <T> T fromJson(String json, Class<T> classOfT) {
        // TODO implement the method fromJson()
        return null;    // Temporary code...
    }
}