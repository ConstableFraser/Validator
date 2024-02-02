package hexlet.code.Shemas;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;


public class StringSchema extends BaseSchema<String> {
    private Predicate<String> isContains;
    private Predicate<String> isRequired;
    private Predicate<String> isGreaterMin;
    private final Map<String, Predicate<String>> listChecks;

    public StringSchema() {
        isRequired = (s) -> true;
        isContains = (s) -> true;
        isGreaterMin = (s) -> true;

        listChecks = new HashMap<>();
        listChecks.put("isRequired", isRequired);
        listChecks.put("isContains", isContains);
        listChecks.put("isGreaterMin", isGreaterMin);
    }

    public StringSchema contains(String searchString) {
        isContains = (s) -> s.contains(searchString);
        listChecks.put("isContains", isContains);
        return this;
    }

    public StringSchema minLength(int minLength) {
        isGreaterMin = (s) -> s.length() >= minLength;
        listChecks.put("isGreaterMin", isGreaterMin);
        return this;
    }

    public StringSchema required() {
        isRequired = (s) -> (s != null && !s.isEmpty());
        listChecks.put("isRequired", isRequired);
        return this;
    }

    @Override
    public Boolean isValid(String value) {
        return listChecks.values()
                .stream()
                .allMatch(check -> check.test(value));
    }
}
