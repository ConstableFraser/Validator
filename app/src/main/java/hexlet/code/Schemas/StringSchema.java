package hexlet.code.Schemas;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;


public class StringSchema extends BaseSchema<String> {
    private final Map<String, Predicate<String>> listChecks;

    public StringSchema() {
        listChecks = new HashMap<>();
        listChecks.put("isRequired", (s) -> true);
        listChecks.put("isContains", (s) -> true);
        listChecks.put("isGreaterMin", (s) -> true);
    }

    public StringSchema contains(String searchString) {
        listChecks.put("isContains", (s) -> s.contains(searchString));
        return this;
    }

    public StringSchema minLength(int minLength) {
        listChecks.put("isGreaterMin", (s) -> s.length() >= minLength);
        return this;
    }

    public StringSchema required() {
        listChecks.put("isRequired", (s) -> (s != null && !s.isEmpty()));
        return this;
    }

    @Override
    public Boolean isValid(String value) {
        return listChecks.values()
                .stream()
                .allMatch(check -> check.test(value));
    }
}
