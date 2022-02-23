package org.bulletin_board.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.bulletin_board.domain.author.Author;
import org.bulletin_board.domain.author.Email;
import org.bulletin_board.domain.author.Phone;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthorDTO {
    String firstName;
    String lastName;
    int age;
    String address;
    List<String> emails;
    List<String> phones;

    public AuthorDTO(Author author) {
        this.firstName = author.getFirstName();
        this.lastName = author.getLastName();
        this.age = author.getAge();

        this.address = String.format("%s %d, %s, %s",
                author.getAddress().getStreet(),
                author.getAddress().getHomeNumber(),
                author.getAddress().getCity(),
                author.getAddress().getCountry());

        this.emails = author.getEmails().stream().map(Email::getName).collect(Collectors.toList());
        this.phones = author.getPhones().stream().map(Phone::getPhoneNumber).collect(Collectors.toList());
    }
}
