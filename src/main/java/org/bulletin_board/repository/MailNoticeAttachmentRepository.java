package org.bulletin_board.repository;

import org.bulletin_board.domain.model.mail.MailNoticeAttachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailNoticeAttachmentRepository extends JpaRepository<MailNoticeAttachment, Long> {
}
