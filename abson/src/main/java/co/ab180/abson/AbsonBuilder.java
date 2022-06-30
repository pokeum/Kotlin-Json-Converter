package co.ab180.abson;

import static co.ab180.abson.Abson.DEFAULT_SERIALIZE_NULLS;
import static co.ab180.abson.Abson.DEFAULT_PRETTY_PRINT;
import static co.ab180.abson.Abson.DEFAULT_ESCAPE_HTML;

public final class AbsonBuilder {

    private boolean serializeNulls = DEFAULT_SERIALIZE_NULLS;
    private boolean prettyPrinting = DEFAULT_PRETTY_PRINT;
    private boolean htmlSafe = DEFAULT_ESCAPE_HTML;

    public AbsonBuilder() { }

    public AbsonBuilder serializeNulls() {
        this.serializeNulls = true;
        return this;
    }

    public Abson create() {
        return new Abson(serializeNulls, prettyPrinting, htmlSafe);
    }
}
