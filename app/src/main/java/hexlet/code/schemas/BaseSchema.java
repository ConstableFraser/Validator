package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected Map<String, Predicate<T>> checks = new LinkedHashMap<>();
    protected final void addCheck(String nameCheck, Predicate<T> condition) {
        checks.put(nameCheck, condition);
    }
    public final Boolean isValid(T obj) {
        return checks.values()
                .stream()
                .allMatch(check -> check.test(obj));
    }
}
