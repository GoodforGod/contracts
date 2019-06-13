package io.uml.contracts.repository;

import io.uml.contracts.model.dao.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 09.06.2019
 */
@Repository
public interface ContractRepository extends JpaRepository<Contract, String> {

    List<Contract> findAllByClient_Id(@Param("id") String clientId);
}
