package hexlet.code;

import hexlet.code.Shemas.MapSchema;
import hexlet.code.Shemas.NumberSchema;
import hexlet.code.Shemas.StringSchema;

public class Validator {
    private final StringSchema stringSchema;
    private final NumberSchema numberSchema;
    private final MapSchema mapSchema;

    public Validator() {
        stringSchema = new StringSchema();
        numberSchema = new NumberSchema();
        mapSchema = new MapSchema();
    }

    public StringSchema string() {
        return stringSchema;
    }

    public NumberSchema number() {
        return numberSchema;
    }

    public MapSchema map() {
        return mapSchema;
    }
}
