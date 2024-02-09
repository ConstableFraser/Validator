package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public final class MapSchema extends BaseSchema<Map<String, String>> {
    private Map<String, BaseSchema<String>> listChecksValues;

    public MapSchema() {
        checks.put("isRequired", (s) -> true);
        checks.put("isCorrectSizing", (s) -> true);
    }

    public MapSchema required() {
        addCheck("isRequired", (n) -> !(n == null));
        return this;
    }

    public MapSchema sizeof(int size) {
        addCheck("isCorrectSizing", (n) -> (n != null && n.size() == size));
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<String>> schemas) {
        listChecksValues = new HashMap<>(schemas);
        return this;
    }

    @Override
    public Boolean isValid(Map<String, String> mapForVerifying) {
        boolean isValidMap = checks.values()
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
