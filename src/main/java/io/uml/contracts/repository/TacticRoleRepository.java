package io.uml.contracts.repository;

import io.uml.contracts.model.dao.Tactic;
import io.uml.contracts.model.dao.TacticRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.12.2019
 */
@Repository
public interface TacticRoleRepository extends JpaRepository<TacticRole, String> {

}
