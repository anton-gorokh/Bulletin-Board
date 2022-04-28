package org.bulletin_board.domain.model.mail;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.bulletin_board.domain.model.AuditModel;
import org.bulletin_board.domain.util.StringListToStringConverter;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class MailNotice extends AuditModel {

    @Column(nullable = false)
    private String subject;

    @Lob
    @Convert(converter = StringListToStringConverter.class)
    @Column(nullable = false)
    private List<String> recipients;

    @Lob
    @Column(nullable = false)
    private String content;

    @OneToMany(mappedBy = MailNoticeAttachment_.MAIL_NOTICE)
    private Set<MailNoticeAttachment> attachments = new HashSet<>();

    @Convert(converter = Jsr310JpaConverters.InstantConverter.class)
    private Instant sentAt;

    @Builder
    public MailNotice(Long id, Instant createdAt, Instant updatedAt, String subject, List<String> recipients,
                      String content, Instant sentAt, Set<MailNoticeAttachment> attachments) {
        super(id, createdAt, updatedAt);
        this.subject = subject;
        this.recipients = recipients;
        this.content = content;
        this.sentAt = sentAt;
        this.attachments = attachments;
    }
}

