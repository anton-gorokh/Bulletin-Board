package org.bulletin_board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleValue implements Comparable<SimpleValue> {
    private Long id;

    private String name;

    @Override
    public int compareTo(SimpleValue simpleValue) {
        return Long.compare(id, simpleValue.getId());
    }
}
