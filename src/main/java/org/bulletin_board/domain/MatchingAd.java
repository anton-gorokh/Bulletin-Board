package org.bulletin_board.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.bulletin_board.domain.author.Author;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Entity
@Table(name = "MatchingAd")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
public class MatchingAd {
    @Id
    @Column(name = "mad_id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @PositiveOrZero
    @Column(scale = 2)
    BigDecimal priceFrom;

    @PositiveOrZero
    @Column(scale = 2)
    BigDecimal priceTo;

    @NotNull
    @Valid
    @OneToOne(cascade = {CascadeType.MERGE},
            fetch = FetchType.EAGER)
    Rubric rubric;

    @NotNull
    @Valid
    @OneToOne(cascade = {CascadeType.MERGE},
            fetch = FetchType.EAGER)
    Author author;

    String title;
}
