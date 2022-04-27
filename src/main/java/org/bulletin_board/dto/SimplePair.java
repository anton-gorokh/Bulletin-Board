package org.bulletin_board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimplePair {
    private String first;
    private String second;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimplePair that = (SimplePair) o;
        return (Objects.equals(first, that.first) && Objects.equals(second, that.second)) ||
                (Objects.equals(first, that.second) && Objects.equals(second, that.first));
    }
}
