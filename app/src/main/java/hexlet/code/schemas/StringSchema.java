package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {

    public StringSchema() {
        addCheck(required, (s) -> true);
        addCheck("isContains", (s) -> true);
        addCheck("isGreaterMin", (s) -> true);
    }

    public StringSchema contains(String searchString) {
        addCheck("isContains", (s) -> s.contains(searchString));
        return this;
    }

    public StringSchema minLength(int minLength) {
        addCheck("isGreaterMin", (s) -> s.length() >= minLength);
        return this;
    }

    public StringSchema required() {
        addCheck(required, (s) -> (s != null && !s.isEmpty()));
        return this;
    }

    @Override
    public Boolean isValid(String value) {
        return checks.values()
                .stream()
                .allMatch(check -> check.test(value));
    }
}
