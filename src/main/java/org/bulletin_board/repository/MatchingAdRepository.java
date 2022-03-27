package org.bulletin_board.repository;

import org.bulletin_board.domain.MatchingAd;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchingAdRepository extends JpaRepository<MatchingAd, Integer> {

}
