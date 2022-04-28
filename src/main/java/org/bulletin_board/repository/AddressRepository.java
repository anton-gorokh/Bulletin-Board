package org.bulletin_board.repository;

import org.bulletin_board.domain.model.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
