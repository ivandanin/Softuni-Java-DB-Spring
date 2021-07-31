package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.domain.entities.Player;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {

    @Query("SELECT p FROM Player p WHERE p.salary > 100000 ORDER BY p.salary DESC")
    List<Player> exportPlayersWhereSalaryBiggerThan();

    @Query("SELECT p FROM Player p WHERE p.team = 5 ORDER BY p.id")
    List<Player> exportPlayersInATeam();
}
