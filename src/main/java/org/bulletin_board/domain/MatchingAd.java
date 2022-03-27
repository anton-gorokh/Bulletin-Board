package org.bulletin_board.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.bulletin_board.domain.author.Author;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.List;

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
    @Column(scale = 2, name = "price_From")
    BigDecimal priceFrom;

    @PositiveOrZero
    @Column(scale = 2, name = "price_to")
    BigDecimal priceTo;

    @NotNull
    @Valid
    @OneToOne(cascade = {CascadeType.MERGE},
            fetch = FetchType.EAGER)
    @JoinColumn(name = "rubric_id")
    Rubric rubric;

    @NotNull
    @Valid
    @OneToOne(cascade = {CascadeType.MERGE},
            fetch = FetchType.EAGER)
    @JoinColumn(name = "mad_creator")
    Author creator;

    @NotNull
    @Valid
    @OneToMany(cascade = {CascadeType.MERGE},
            fetch = FetchType.EAGER)
    @JoinTable(name = "Mad_Author",
            joinColumns = @JoinColumn(name = "mad_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = "mad_id"))
    List<Author> authors;

    String title;
}
