package io.uml.contracts.repository;

import io.uml.contracts.model.dao.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 09.06.2019
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, String> {

}
