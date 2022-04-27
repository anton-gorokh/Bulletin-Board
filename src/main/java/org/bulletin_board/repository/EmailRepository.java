package org.bulletin_board.repository;

import org.bulletin_board.domain.board.Category;
import org.bulletin_board.domain.board.author.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface EmailRepository extends JpaRepository<Email, Long> {

    /**
     * Returns a list of string emails whose mad matches input announcement fields
     *
     * @param category rubric to be filtered by
     * @param name announcement's name to be filtered by
     * @param pay pay to be filtered by
     * @return emails in string
     */
    @Query("select mails.name from AnnouncementFilter filter join filter.author author join author.emails mails " +
            "where filter.category = :category and filter.title like :name and filter.priceFrom <= :pay and filter.priceTo >= :pay")
    List<String> findEmails(@Param("category") Category category,
                            @Param("name") String name,
                            @Param("pay") BigDecimal pay);
}
