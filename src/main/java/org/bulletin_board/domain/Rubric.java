package org.bulletin_board.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Rubric")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
public class Rubric {
    @Id
    @Column(name = "rubric_id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NotBlank(message = "Rubric name cannot be empty")
    String name;

    @Override
    public String toString() {
        return "Rubric: " +
                "id = " + id +
                ", name = '" + name + '\'';
    }
}
