package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplicationTest {

    @Test
    void testMethodsSchemaString() {
        Validator v = new Validator();

        var schema = v.string();
        assertEquals(true, schema.isValid(""));
        assertEquals(true, schema.isValid(null));
        assertEquals(false, schema.isValid(7));

        assertEquals(schema, schema.required());
        assertEquals(false, schema.isValid(""));
        assertEquals(false, schema.isValid(null));

        assertEquals(schema, schema.contains(""));
        assertEquals(false, schema.isValid(""));
        assertEquals(true, schema.isValid("test string"));

        schema.contains("World");
        assertEquals(true, schema.isValid("Hello, World!"));
        assertEquals(false, schema.isValid("eat some more of these French rolls"));

        assertEquals(schema, schema.minLength(13));
        assertEquals(true, schema.isValid("Hello, World!"));

        schema.contains("").minLength(4);
        assertEquals(false, schema.isValid("zyx"));
    }
}
