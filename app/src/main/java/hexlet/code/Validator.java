package hexlet.code;

import hexlet.code.Shemas.StringSchema;

public class Validator {
    private final StringSchema stringSchema;

    public Validator() {
        stringSchema = new StringSchema();
    }

    public StringSchema string() {
        return stringSchema;
    }
}
