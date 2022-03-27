package org.bulletin_board.repository;

import org.bulletin_board.domain.Announcement;
import org.bulletin_board.domain.Rubric;
import org.bulletin_board.domain.author.Author;
import org.bulletin_board.domain.author.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface EmailRepository extends JpaRepository<Email, Integer> {

    /**
     * Returns a list of string emails whose mad matches input announcement fields
     *
     * @param author announcement author to be filtered by
     * @param rubric rubric to be filtered by
     * @param name announcement's name to be filtered by
     * @param pay pay to be filtered by
     * @return emails in string
     */
    @Query("SELECT emails.name FROM MatchingAd mad " +
            "JOIN Author au ON mad.creator = au " +
            "JOIN au.emails emails " +
            "JOIN mad.authors authors " +
            "WHERE :author IN authors " +
            "AND mad.rubric = :rubric " +
            "AND mad.priceFrom <= :pay " +
            "AND mad.priceTo >= :pay " +
            "AND mad.title LIKE :name ")
    List<String> findEmails(@Param("author") Author author,
                            @Param("rubric") Rubric rubric,
                            @Param("name") String name,
                            @Param("pay") BigDecimal pay);
}
