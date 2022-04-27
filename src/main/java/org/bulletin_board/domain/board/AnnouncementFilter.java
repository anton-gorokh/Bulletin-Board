package org.bulletin_board.domain.board;

import lombok.*;
import org.bulletin_board.domain.AbstractEntity;
import org.bulletin_board.domain.board.author.Author;
import org.bulletin_board.dto.util.converters.LocalDateConverter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class AnnouncementFilter extends AbstractEntity {

    @PositiveOrZero
    @Column(scale = 2, nullable = false)
    private BigDecimal priceFrom;

    @PositiveOrZero
    @Column(scale = 2, nullable = false)
    private BigDecimal priceTo;

    @Convert(converter = LocalDateConverter.class)
    private LocalDate from;

    @Convert(converter = LocalDateConverter.class)
    private LocalDate to;

    @Column(nullable = false)
    private String title;

    @NotNull
    @Valid
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Category category;

    @NotNull
    @Valid
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private Author author;

    @Builder
    public AnnouncementFilter(Long id, BigDecimal priceFrom, BigDecimal priceTo, LocalDate from, LocalDate to,
                              String title, Category category, Author author) {
        super(id);
        this.priceFrom = priceFrom;
        this.priceTo = priceTo;
        this.from = from;
        this.to = to;
        this.title = title;
        this.category = category;
        this.author = author;
    }
}
