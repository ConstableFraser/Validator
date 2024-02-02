package hexlet.code.Shemas;

public abstract class BaseSchema<T> {
    public abstract Boolean isValid(T obj);
}
