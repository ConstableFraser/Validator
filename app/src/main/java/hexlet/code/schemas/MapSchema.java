package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema<Map<String, String>> {

    public MapSchema() {
        checks.put("isRequired", (s) -> true);
        checks.put("isCorrectSizing", (s) -> true);
        checks.put("isCorrectValue", (s) -> true);
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
        addCheck("isCorrectValue", (map) -> map.entrySet()
                .stream()
                .allMatch((e) -> (schemas.get(e.getKey()).isValid(e.getValue()))));
        return this;
    }
}
