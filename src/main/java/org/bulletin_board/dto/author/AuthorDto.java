package org.bulletin_board.dto.author;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bulletin_board.dto.SimpleValue;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorDto {
    Long id;
    String userName;
    String password;
    String firstName;
    String lastName;
    int age;

    SimpleValue address;
    List<SimpleValue> phones;
    List<SimpleValue> emails;
}
