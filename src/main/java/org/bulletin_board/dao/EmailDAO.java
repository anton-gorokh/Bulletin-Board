package org.bulletin_board.dao;

import org.bulletin_board.domain.Announcement;

import java.util.List;

public interface EmailDAO {
    List<String> findEmails(Announcement announcement);
}
