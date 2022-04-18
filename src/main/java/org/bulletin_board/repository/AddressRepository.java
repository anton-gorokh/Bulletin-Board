package org.bulletin_board.repository;

import org.bulletin_board.domain.board.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
