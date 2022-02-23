package org.bulletin_board.service;

import org.bulletin_board.domain.Announcement;

public interface EmailService {
    void sendEmails(Announcement announcement);
}
