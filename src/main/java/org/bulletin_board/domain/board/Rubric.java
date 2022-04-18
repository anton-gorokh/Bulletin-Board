package org.bulletin_board.domain.board;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.bulletin_board.domain.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@ToString
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
public class Rubric extends AbstractEntity {

    @NotBlank(message = "Rubric name cannot be empty")
    String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = Announcement_.RUBRIC)
    Set<Announcement> announcements;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = AnnouncementFilter_.RUBRIC)
    Set<AnnouncementFilter> filters;

    @Builder
    public Rubric(Long id, String name, Set<Announcement> announcements) {
        super(id);
        this.name = name;
        this.announcements = announcements;
    }
}
