package org.modelmapperexercise.data.repositories;

import org.modelmapperexercise.data.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository <Game,Long>{
}
