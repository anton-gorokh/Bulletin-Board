package org.bulletin_board.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.bulletin_board.domain.author.Author;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Announcement")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
public class Announcement {
    @Id
    @Column(name = "announcement_id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NotBlank(message = "Announcement name cannot be empty")
    String name;

    @Column(name = "creation_date")
    @Temporal(TemporalType.DATE)
    Date creationDate;

    @NotBlank(message = "Announcement text cannot be empty")
    String text;

    @PositiveOrZero(message = "Pay cannot be less than zero")
    @Column(scale = 2)
    BigDecimal pay;

    @NotNull(message = "Author cannot be null")
    @Valid
    @ManyToOne(cascade = {CascadeType.MERGE},
            fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_Ad_Author")
    Author author;

    @NotNull(message = "Rubric cannot be null")
    @Valid
    @ManyToOne(cascade = {CascadeType.MERGE},
            fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_Ad_Rubric")
    Rubric rubric;

    @Override
    public String toString() {
        return "Announcement: " +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", creationDate = " + creationDate +
                ", text = '" + text + '\'' +
                ", pay = " + pay +
                ", author = " + author +
                ", rubric = " + rubric;
    }
}
