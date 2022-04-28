package org.bulletin_board.domain.model.author;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.bulletin_board.domain.model.AbstractEntity;
import org.bulletin_board.domain.model.SimpleValueConvertible;
import org.bulletin_board.dto.SimpleValue;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
public class Email extends AbstractEntity implements SimpleValueConvertible {

    @Pattern(regexp = "(.+)@(.+)(\\..+)")
    @NotBlank(message = "Email cannot be empty")
    @Column(nullable = false)
    String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    Author author;

    @Builder
    public Email(Long id, String name, Author author) {
        super(id);
        this.name = name;
        this.author = author;
    }

    @Override
    public SimpleValue toSimpleValue() {
        return new SimpleValue(this.id, this.name);
    }

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
