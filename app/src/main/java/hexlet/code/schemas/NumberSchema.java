package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class NumberSchema extends BaseSchema<Integer> {
    private final Map<String, Predicate<Integer>> listChecks;

    public NumberSchema() {
        listChecks = new HashMap<>();
        listChecks.put("isRequired", (s) -> true);
        listChecks.put("isPositive", (s) -> true);
        listChecks.put("inRange", (s) -> true);
    }

    public NumberSchema required() {
        listChecks.put("isRequired", (n) -> !(n == null));
        return this;
    }

    public NumberSchema positive() {
        listChecks.put("isPositive", (n) -> (n == null || n > 0));
        return this;
    }

    public NumberSchema range(int min, int max) {
        listChecks.put("inRange", (n) -> (n >= min && n <= max));
        return this;
    }

    @Override
    public Boolean isValid(Integer value) {
        return listChecks.values()
                .stream()
                .allMatch(check -> check.test(value));
    }
}
