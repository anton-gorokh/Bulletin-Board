package org.bulletin_board.domain.board;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.bulletin_board.domain.AuditModel;
import org.bulletin_board.domain.board.author.Author;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
public class Announcement extends AuditModel {
    @NotBlank
    @Column(nullable = false)
    String name;

    @NotBlank
    @Column(nullable = false)
    String text;

    @PositiveOrZero
    @Column(scale = 2)
    BigDecimal pay;

    @Column(nullable = false)
    Boolean active;

    @Valid
    @NotNull
    @ManyToOne(optional = false)
    Author author;

    @Valid
    @NotNull
    @ManyToOne(optional = false)
    Category category;

    @Builder
    public Announcement(Long id, Instant createdAt, Instant updatedAt, String name, String text, BigDecimal pay,
                        Author author, Category category, boolean active) {
        super(id, createdAt, updatedAt);
        this.name = name;
        this.text = text;
        this.pay = pay;
        this.author = author;
        this.category = category;
        this.active = active;
    }
}
