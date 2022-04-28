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
import javax.validation.constraints.Size;
import java.util.Objects;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
public class Phone extends AbstractEntity implements SimpleValueConvertible {

    @Size(max = 16)
    @NotBlank(message = "Phone number cannot be empty")
    @Column(name = "phone_number")
    String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    Author author;

    @Builder
    public Phone(Long id, String phoneNumber, Author author) {
        super(id);
        this.phoneNumber = phoneNumber;
        this.author = author;
    }

    @Override
    public SimpleValue toSimpleValue() {
        return new SimpleValue(this.id, this.phoneNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return id == phone.id && phoneNumber.equals(phone.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phoneNumber);
    }
}
