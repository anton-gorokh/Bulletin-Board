package org.bulletin_board.repository;

import org.bulletin_board.domain.model.mail.MailNotice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailNoticeRepository extends JpaRepository<MailNotice, Long> {
}
