package bg.softuni.springdatalab.repositories;

import bg.softuni.springdatalab.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
User findByUsername();
}
