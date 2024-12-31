package ma.mundia.digitalbankapp.Repositories;

import ma.mundia.digitalbankapp.Entities.AccountOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountOperationRepository extends JpaRepository<AccountOperation, Long> {
    Page<AccountOperation> findByBankAccountId(String accountId, Pageable pageable);

    List<AccountOperation> findByBankAccountId(String accountId);
}
