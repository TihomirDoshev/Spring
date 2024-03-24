package bg.softuni.springdatalab.repositories;

import bg.softuni.springdatalab.models.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account , Integer> {
}
