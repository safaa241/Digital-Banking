package ma.mundia.digitalbankapp.Repositories;

import ma.mundia.digitalbankapp.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
