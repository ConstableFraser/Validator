package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema() {
        checks.put(required, (s) -> true);
        checks.put("isPositive", (s) -> true);
        checks.put("inRange", (s) -> true);
    }

    public NumberSchema required() {
        addCheck("isRequired", (n) -> !(n == null));
        return this;
    }

    public NumberSchema positive() {
        addCheck("isPositive", (n) -> (n == null || n > 0));
        return this;
    }

    public NumberSchema range(int min, int max) {
        addCheck("inRange", (n) -> (n >= min && n <= max));
        return this;
    }
}
