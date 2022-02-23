package org.bulletin_board.domain.address;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
public class Address {
        @Id
        @Column(name = "address_id", updatable = false)
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        int id;

        @NotNull(message = "Country must be selected")
        @Enumerated(EnumType.STRING)
        Country country;

        @NotBlank(message = "City cannot be empty")
        String city;

        @NotBlank(message = "Street cannot be empty")
        String street;

        @PositiveOrZero(message = "Home number cannot be less than zero")
        @Column(name = "home_number")
        int homeNumber;

        @Override
        public String toString() {
                return "Address: " +
                        "id = " + id +
                        ", country = " + country +
                        ", city = '" + city + '\'' +
                        ", street = '" + street + '\'' +
                        ", homeNumber = " + homeNumber;
        }
}














