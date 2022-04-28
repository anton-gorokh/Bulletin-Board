package org.bulletin_board.domain.util;

import liquibase.util.StringUtil;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.List;

@Converter
public class StringListToStringConverter implements AttributeConverter<List<String>, String> {
    private static final String DELIMETER = "\n";

    @Override
    public String convertToDatabaseColumn(List<String> entity) {
        if (entity.isEmpty()) {
            return null;
        }
        return String.join(DELIMETER, entity);
    }

    @Override
    public List<String> convertToEntityAttribute(String dbColumn) {
        if (StringUtil.isEmpty(dbColumn)) {
            return null;
        }
        return Arrays.asList(dbColumn.split(DELIMETER));
    }
}
