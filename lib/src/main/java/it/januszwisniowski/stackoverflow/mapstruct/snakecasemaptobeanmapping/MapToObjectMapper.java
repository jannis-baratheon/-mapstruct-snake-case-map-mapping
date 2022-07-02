package it.januszwisniowski.stackoverflow.mapstruct.snakecasemaptobeanmapping;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.Map;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toMap;

@Mapper
public interface MapToObjectMapper {
    MapToObjectMapper INSTANCE = Mappers.getMapper(MapToObjectMapper.class);

    private static String snakeToCamel(String snakeCaseString) {
        // https://stackoverflow.com/a/67605103/4494577
        return Pattern.compile("_([a-z])")
                .matcher(snakeCaseString)
                .replaceAll(m -> m.group(1).toUpperCase());
    }

    MyObject toMyObject(Map<String, String> map);

    default MyObject toMyObjectFromSnakeCaseMap(Map<String, String> snakeKeyPropertyMap) {
        return toMyObject(snakeKeyPropertyMap.entrySet().stream()
                .collect(collectingAndThen(
                        toMap(s -> snakeToCamel(s.getKey()), Map.Entry::getValue),
                        Collections::unmodifiableMap)));
    }
}
