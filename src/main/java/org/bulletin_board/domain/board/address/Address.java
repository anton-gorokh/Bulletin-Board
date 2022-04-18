package org.bulletin_board.domain.board.address;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.bulletin_board.domain.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Address extends AbstractEntity {
    @NotNull(message = "Country must be selected")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    Country country;

    @NotBlank(message = "City cannot be empty")
    @Column(nullable = false)
    String city;

    @NotBlank(message = "Street cannot be empty")
    @Column(nullable = false)
    String street;

    @NotBlank(message = "Postal code cannot be empty")
    @Column(nullable = false)
    String postalCode;

    @Builder
    public Address(Long id, Country country, String city, String street, String postalCode) {
        super(id);
        this.country = country;
        this.city = city;
        this.street = street;
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return postalCode + " " + street + ", " + city + ", " + country;
    }
}














