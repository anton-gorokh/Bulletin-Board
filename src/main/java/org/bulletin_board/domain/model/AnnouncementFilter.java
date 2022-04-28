package org.bulletin_board.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bulletin_board.domain.model.author.Author;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
    @Column(scale = 2)
    private BigDecimal priceFrom;

    @PositiveOrZero
    @Column(scale = 2)
    private BigDecimal priceTo;

    private LocalDate from;

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
