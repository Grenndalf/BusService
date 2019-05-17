package pl.spring.finalProject.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Query ( value = "select tr.first_name,tr.last_name,tr.email from traveler tr where tr.login=?1", nativeQuery = true )
    Traveler getTravelerData(String login);

    @Query (value = "select new Map(t.firstName AS firstName, t.lastName AS lastName,t.email as email) from Traveler t where login = ?1")
    HashMap getFirstAndLastnameAndEmailByLogin (String login);

    @Modifying
    @Query (value = "Update traveler tr set tr.first_name=?1,tr.last_name=?2,tr.email=?3 where tr.login=?4", nativeQuery = true)
    void updateUserData(String firstName,String lastName,String email,String login);
}