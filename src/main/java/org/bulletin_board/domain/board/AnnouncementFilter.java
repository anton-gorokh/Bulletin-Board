package org.bulletin_board.domain.board;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.bulletin_board.domain.AbstractEntity;
import org.bulletin_board.domain.board.author.Author;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class AnnouncementFilter extends AbstractEntity {

    @PositiveOrZero
    @Column(scale = 2, nullable = false)
    BigDecimal priceFrom;

    @PositiveOrZero
    @Column(scale = 2, nullable = false)
    BigDecimal priceTo;

    @Column(nullable = false)
    String title;

    @NotNull
    @Valid
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    Rubric rubric;

    @NotNull
    @Valid
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    Author author;

    @Builder
    public AnnouncementFilter(Long id, BigDecimal priceFrom, BigDecimal priceTo, Rubric rubric, Author author, String title) {
        super(id);
        this.priceFrom = priceFrom;
        this.priceTo = priceTo;
        this.rubric = rubric;
        this.author = author;
        this.title = title;
    }
}
