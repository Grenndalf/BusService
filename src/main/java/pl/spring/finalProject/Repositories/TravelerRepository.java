package pl.spring.finalProject.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.spring.finalProject.domain.entities.Traveler;

@Repository
public interface TravelerRepository extends JpaRepository<Traveler, Long> {

    @Query(value = "SELECT CASE WHEN count(t) = 0 THEN true ELSE false END FROM Traveler t WHERE login = ?1")
    Boolean isTravelerLoginIsAvailable(String login);

}