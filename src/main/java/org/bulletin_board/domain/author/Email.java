package org.bulletin_board.domain.author;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@Entity
@Table(name = "Email")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder

public class Email {
    @Id
    @Column(name = "email_id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Pattern(regexp = "(.+)@(.+)(\\..+)")
    @NotBlank(message = "Email cannot be empty")
    String name;

    @Valid
    @ManyToOne
    Author author;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return id == email.id && name.equals(email.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
