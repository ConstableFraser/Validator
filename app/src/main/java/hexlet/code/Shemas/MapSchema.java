package hexlet.code.Shemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema<Map<String, String>> {
    private final Map<String, Predicate<Map<?, ?>>> listChecksMap;
    private Map<String, BaseSchema<String>> listChecksValues;

    public MapSchema() {
        listChecksMap = new HashMap<>();
        listChecksMap.put("isRequired", (s) -> true);
        listChecksMap.put("isCorrectSizing", (s) -> true);
    }

    public MapSchema required() {
        listChecksMap.put("isRequired", (n) -> !(n == null));
        return this;
    }

    public MapSchema sizeof(int size) {
        listChecksMap.put("isCorrectSizing", (n) -> (n.size() == size));
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<String>> schemas) {
        listChecksValues = new HashMap<>(schemas);
        return this;
    }

    @Override
    public Boolean isValid(Map<String, String> mapForVerifying) {
        boolean isValidMap = listChecksMap.values()
                .stream()
                .allMatch(check -> check.test(mapForVerifying));

        if (mapForVerifying == null || listChecksValues == null) {
            return isValidMap;
        }

        return isValidMap && mapForVerifying.entrySet()
                .stream()
                .allMatch((e) -> (listChecksValues.get(e.getKey()).isValid(e.getValue())));
    }
}
