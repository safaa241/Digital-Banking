package ma.mundia.digitalbankapp.Repositories;

import ma.mundia.digitalbankapp.Entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {

}
