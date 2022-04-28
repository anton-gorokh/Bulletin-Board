package org.bulletin_board.domain.model.mail;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.bulletin_board.domain.model.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class MailNoticeAttachment extends AbstractEntity {

    @Column(nullable = false)
    private String name;

    @Lob
    @Column(nullable = false)
    private String content;

    @NotNull
    @ManyToOne
    private MailNotice mailNotice;

    @Builder
    public MailNoticeAttachment(Long id, String name, String content, MailNotice mailNotice) {
        super(id);
        this.name = name;
        this.content = content;
        this.mailNotice = mailNotice;
    }
}
