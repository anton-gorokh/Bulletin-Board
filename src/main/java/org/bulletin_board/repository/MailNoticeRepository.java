package org.bulletin_board.repository;

import org.bulletin_board.domain.model.mail.MailNotice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MailNoticeRepository extends JpaRepository<MailNotice, Long> {
    List<MailNotice> findBySentAtIsNull();
}
