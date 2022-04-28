package org.bulletin_board.domain.model.address;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bulletin_board.domain.model.AbstractEntity;
import org.bulletin_board.domain.model.SimpleValueConvertible;
import org.bulletin_board.dto.SimpleValue;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Address extends AbstractEntity implements SimpleValueConvertible {
    @Enumerated(EnumType.STRING)
    private Country country;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String postalCode;

    @Builder
    public Address(Long id, Country country, String city, String street, String postalCode) {
        super(id);
        this.country = country;
        this.city = city;
        this.street = street;
        this.postalCode = postalCode;
    }

    @Override
    public SimpleValue toSimpleValue() {
        return new SimpleValue(this.id, toString());
    }

    @Override
    public String toString() {
        return postalCode + " " + street + ", " + city + ", " + country;
    }
}














