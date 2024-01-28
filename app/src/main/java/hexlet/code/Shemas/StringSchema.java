package hexlet.code.Shemas;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;


public class StringSchema implements BaseSchema {
    private Predicate<String> isContains;
    private Predicate<String> isValidRequired;
    private Predicate<String> isGreaterMin;
    private final Map<String, Predicate<String>> listChecks;
    private Boolean isRequired = false;

    public StringSchema() {
        isValidRequired = (s) -> true;
        isContains = (s) -> true;
        isGreaterMin = (s) -> true;

        listChecks = new HashMap<>();
        listChecks.put("isValidRequired", isValidRequired);
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
        isRequired = true;
        isValidRequired = (s) -> (!s.isEmpty() && isRequired);
        listChecks.put("isValidRequired", isValidRequired);
        return this;
    }

    @Override
    public Boolean isValid(Object obj) {
        if ((obj != null) && !(obj instanceof String)) {
            return false;
        }
        String value = obj == null ? "" : (String) obj;

        return listChecks.values()
                .stream()
                .allMatch(check -> check.test(value));
    }
}
