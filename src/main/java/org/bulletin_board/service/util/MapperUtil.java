package org.bulletin_board.service.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.bulletin_board.domain.model.SimpleValueConvertible;
import org.bulletin_board.dto.SimpleValue;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MapperUtil {

    public static <T extends SimpleValueConvertible> List<SimpleValue> toSimpleValues(Collection<T> toConvert) {
        return toConvert.stream().map(SimpleValueConvertible::toSimpleValue).collect(Collectors.toList());
    }

    public static List<Long> getSimpleValueIds(List<SimpleValue> values) {
        return values.stream().map(SimpleValue::getId).collect(Collectors.toList());
    }

    public static <T> T getValueOrDefault(T value, T defaultValue) {
        return value == null ? defaultValue : value;
    }

    public static <R, T> R processValueOrGetDefault(T value, R defaultValue, Function<T, R> mapper) {
        return value == null ? defaultValue : mapper.apply(value);
    }
}
