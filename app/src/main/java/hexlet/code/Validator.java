package hexlet.code;

import hexlet.code.Shemas.MapSchema;
import hexlet.code.Shemas.NumberSchema;
import hexlet.code.Shemas.StringSchema;

public class Validator {

    public Validator() {
    }

    public StringSchema string() {
        return new StringSchema();
    }

    public NumberSchema number() {
        return new NumberSchema();
    }

    public MapSchema map() {
        return new MapSchema();
    }
}
