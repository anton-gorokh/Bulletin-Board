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
    @NotBlank(message = "Announcement name cannot be empty")
    @Column(nullable = false)
    String name;

    @NotBlank(message = "Announcement text cannot be empty")
    @Column(nullable = false)
    String text;

    @PositiveOrZero(message = "Pay cannot be less than zero")
    @Column(scale = 2)
    BigDecimal pay;

    @Column(nullable = false)
    boolean active;

    @Valid
    @NotNull(message = "Author cannot be null")
    @ManyToOne(optional = false)
    Author author;

    @Valid
    @NotNull(message = "Rubric cannot be null")
    @ManyToOne(optional = false)
    Rubric rubric;

    @Builder
    public Announcement(Long id, Instant createdAt, Instant updatedAt, String name, String text, BigDecimal pay,
                        Author author, Rubric rubric, boolean active) {
        super(id, createdAt, updatedAt);
        this.name = name;
        this.text = text;
        this.pay = pay;
        this.author = author;
        this.rubric = rubric;
        this.active = active;
    }
}
