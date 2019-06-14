package io.uml.contracts.repository;

import io.uml.contracts.model.dao.Mercenary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 09.06.2019
 */
@Repository
public interface MercenaryRepository extends JpaRepository<Mercenary, String> {

    Optional<Mercenary> findByEmail(@Param("email") String email);
}
