package com.example.football.repository;

import com.example.football.models.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Long> {
    boolean existsPlayerByEmail(String email);
    //Order Them by Shooting in Desc Order,
    // Then by Passing in Desc Order,
    // Then by Endurance Desc Order and
    // Finally Then by Player Last Name.
    //"WHERE Players.birthDate > '1995-01-01' AND Players.birthDate < '2003-01-01' "
//    LocalDate startDate = LocalDate.of(1995,1,1);
//    LocalDate endDate = LocalDate.of(2003,1,1);
//    @Query("SELECT p FROM Player p WHERE p.birthDate > '1995-01-01' AND p.birthDate <'2003-01-01' ORDER BY p.stat.shooting desc ," +
//            "p.stat.passing desc ,p.stat.endurance,p.lastName asc ")
//
//    List<Player>allPlayersWithSomeCriteria();

    List<Player>findByBirthDateBetweenOrderByStatShootingDescStatPassingDescStatEnduranceDescLastNameAsc(LocalDate after , LocalDate before);

}
