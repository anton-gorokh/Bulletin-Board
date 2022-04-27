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
import org.bulletin_board.domain.SimpleValueConvertible;
import org.bulletin_board.dto.SimpleValue;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Category extends AbstractEntity implements SimpleValueConvertible {

    @NotBlank
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = Announcement_.CATEGORY)
    private Set<Announcement> announcements;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = AnnouncementFilter_.CATEGORY)
    private Set<AnnouncementFilter> filters;

    @Builder
    public Category(Long id, String name, Set<Announcement> announcements) {
        super(id);
        this.name = name;
        this.announcements = announcements;
    }

    @Override
    public SimpleValue toSimpleValue() {
        return new SimpleValue(this.id, this.name);
    }
}
