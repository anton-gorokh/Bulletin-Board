package org.bulletin_board.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.MappedSuperclass;
import java.time.Instant;

@ToString(callSuper = true)
@Getter
@NoArgsConstructor
@MappedSuperclass
public abstract class AuditModel extends AbstractEntity {
    @Column(nullable = false, updatable = false)
    @CreatedDate
    @Convert(converter = Jsr310JpaConverters.InstantConverter.class)
    protected Instant createdAt;

    @Column(nullable = false)
    @LastModifiedDate
    @Convert(converter = Jsr310JpaConverters.InstantConverter.class)
    protected Instant updatedAt;

    public AuditModel(Long id, Instant createdAt, Instant updatedAt) {
        super(id);
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
