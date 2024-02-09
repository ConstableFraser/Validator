package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected Map<String, Predicate<T>> checks = new LinkedHashMap<>();
    protected String required = "isRequired";
    protected void addCheck(String nameCheck, Predicate<T> condition) {
        checks.put(nameCheck, condition);
    }
    public abstract Boolean isValid(T obj);
}
