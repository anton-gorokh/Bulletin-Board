package org.bulletin_board.service.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ListUtil {
    public static <T> List<T> getValuesOrEmpty(List<T> values) {
        return values == null ? List.of() : values;
    }

    public static <T> List<T> getValuesOrNull(List<T> values) {
        return values == null || values.isEmpty() ? null : values;
    }
}
