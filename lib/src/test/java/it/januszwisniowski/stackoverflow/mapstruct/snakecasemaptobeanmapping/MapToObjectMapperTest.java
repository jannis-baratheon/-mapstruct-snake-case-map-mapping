package it.januszwisniowski.stackoverflow.mapstruct.snakecasemaptobeanmapping;

import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MapToObjectMapperTest {
    @Test
    void mapsMapWithSnakeCasedKeysToMyObject() {
        MapToObjectMapper mapper = MapToObjectMapper.INSTANCE;
        Map<String, String> propertyMap = ImmutableMap.of(
                "some_integer_field", "1",
                "some_string_field", "some string value");

        MyObject bean = mapper.toMyObjectFromSnakeCaseMap(propertyMap);

        assertEquals(bean.someIntegerField(), 1);
        assertEquals(bean.someStringField(), "some string value");
    }
}
