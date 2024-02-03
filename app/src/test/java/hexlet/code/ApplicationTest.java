package hexlet.code;

import hexlet.code.Schemas.BaseSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplicationTest {

    @Test
    void testMethodsSchemaString() {
        Validator v = new Validator();

        var schema = v.string();
        assertEquals(true, schema.isValid(""));
        assertEquals(true, schema.isValid(null));

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

    @Test
    void testMethodsSchemaNumber() {
        Validator v = new Validator();

        var schema = v.number();
        assertEquals(true, schema.isValid(5));
        assertEquals(true, schema.isValid(null));

        assertEquals(schema, schema.positive());
        assertEquals(true, schema.isValid(null));

        assertEquals(schema, schema.required());
        assertEquals(false, schema.isValid(null));
        assertEquals(true, schema.isValid(10));
        assertEquals(false, schema.isValid(-10));
        assertEquals(false, schema.isValid(0));

        assertEquals(schema, schema.range(5, 10));
        assertEquals(true, schema.isValid(5));
        assertEquals(true, schema.isValid(10));
        assertEquals(false, schema.isValid(4));
        assertEquals(false, schema.isValid(11));
    }

    @Test
    void testMethodsSchemaMap() {
        Validator v = new Validator();

        var schema = v.map();
        assertEquals(true, schema.isValid(null));

        assertEquals(schema, schema.required());
        assertEquals(false, schema.isValid(null));
        assertEquals(true, schema.isValid(new HashMap<>()));

        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        assertEquals(true, schema.isValid(data));

        assertEquals(schema, schema.sizeof(2));
        assertEquals(false, schema.isValid(data));
        data.put("key2", "value2");
        assertEquals(true, schema.isValid(data));
    }

    @Test
    void testMethodsShapeMap() {
        Validator v = new Validator();

        var schema = v.map();
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", v.string().required());
        schemas.put("surName", v.string());
        schemas.put("lastName", v.string().required().minLength(2));

        assertEquals(schema, schema.shape(schemas));

        Map<String, String> person1 = new HashMap<>();
        person1.put("firstName", "Li");
        person1.put("surName", "");
        person1.put("lastName", "Bu");
        assertEquals(true, schema.isValid(person1));


        schemas.put("firstName", v.string().required());
        schemas.remove("surName");
        schemas.put("lastName", v.string().required());

        schema.shape(schemas);

        Map<String, String> person2 = new HashMap<>();
        person2.put("firstName", "Anthony");
        person2.put("lastName", null);
        assertEquals(false, schema.isValid(person2));

        Map<String, String> person3 = new HashMap<>();
        person3.put("firstName", "Anna");
        person3.put("lastName", "");
        assertEquals(false, schema.isValid(person3));
    }
}
