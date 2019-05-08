package pl.spring.finalProject.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.spring.finalProject.domain.entities.Traveler;

import java.util.HashMap;

@Repository
public interface TravelerRepository extends JpaRepository<Traveler, Long> {

    @Query(value = "SELECT CASE WHEN count(t) = 0 THEN true ELSE false END FROM Traveler t WHERE login = ?1")
    Boolean isTravelerLoginIsAvailable(String login);

    Traveler findByLogin(String login);

    @Query (value = "select new Map(t.firstName AS firstName, t.lastName AS lastName) from Traveler t where login = ?1")
    HashMap getFirstAndLastnameByLogin (String login);
}