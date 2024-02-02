package hexlet.code.Shemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class NumberSchema extends BaseSchema<Integer> {
    private Predicate<Integer> isNotNull;
    private Predicate<Integer> isPositive;
    private Predicate<Integer> inRange;
    private final Map<String, Predicate<Integer>> listChecks;

    public NumberSchema() {
        isNotNull = (s) -> true;
        isPositive = (s) -> true;
        inRange = (s) -> true;

        listChecks = new HashMap<>();
        listChecks.put("isRequired", isNotNull);
        listChecks.put("isPositive", isPositive);
        listChecks.put("inRange", inRange);
    }

    public NumberSchema required() {
        isNotNull = (n) -> !(n == null);
        listChecks.put("isRequired", isNotNull);
        return this;
    }

    public NumberSchema positive() {
        isPositive = (n) -> (n == null || n > 0);
        listChecks.put("isPositive", isPositive);
        return this;
    }

    public NumberSchema range(int min, int max) {
        inRange = (n) -> (n >= min && n <= max);
        listChecks.put("inRange", inRange);
        return this;
    }

    @Override
    public Boolean isValid(Integer value) {
        return listChecks.values()
                .stream()
                .allMatch(check -> check.test(value));
    }
}
