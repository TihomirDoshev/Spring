package bg.softuni.pathfinder.repository;

import bg.softuni.pathfinder.model.entites.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Route,Long> {
}
