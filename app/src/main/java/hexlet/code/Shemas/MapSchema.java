package hexlet.code.Shemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema<Map<?, ?>> {
    private Predicate<Map<?, ?>> isNotNull;
    private Predicate<Map<?, ?>> isCorrectSizing;
    private final Map<String, Predicate<Map<?, ?>>> listChecks;

    public MapSchema() {
        isNotNull = (s) -> true;
        isCorrectSizing = (s) -> true;

        listChecks = new HashMap<>();
        listChecks.put("isRequired", isNotNull);
        listChecks.put("isCorrectSizing", isCorrectSizing);
    }

    public MapSchema required() {
        isNotNull = (n) -> !(n == null);
        listChecks.put("isRequired", isNotNull);
        return this;
    }

    public MapSchema sizeof(int size) {
        isCorrectSizing = (n) -> (n.size() == size);
        listChecks.put("isCorrectSizing", isCorrectSizing);
        return this;
    }

    @Override
    public Boolean isValid(Map<?, ?> value) {
        return listChecks.values()
                .stream()
                .allMatch(check -> check.test(value));
    }
}
