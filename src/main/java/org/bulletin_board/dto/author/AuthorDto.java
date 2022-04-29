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
    private Long id;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private int age;

    private SimpleValue address;
    private List<String> phones;
    private List<String> emails;
}
