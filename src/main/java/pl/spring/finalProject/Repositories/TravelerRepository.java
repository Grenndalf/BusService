package pl.spring.finalProject.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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

    @Query ( value = "select new Map( t.firstName AS firstName, t.lastName AS lastName, t.email AS email) from Traveler t where login = ?1" )
    HashMap getFirstAndLastnameAndEmailByLogin (String login);

    @Transactional
    @Modifying
    @Query (value = "UPDATE traveler set first_name=?1 where traveler.login=?2",nativeQuery = true)
    void  updateUserFirstName(String firstName,String login);

    @Transactional
    @Modifying
    @Query (value = "UPDATE traveler set last_name=?1 where traveler.login=?2",nativeQuery = true)
    void  updateUserLastName(String lastName,String login);

    @Transactional
    @Modifying
    @Query (value = "UPDATE traveler set email=?1 where traveler.login=?2",nativeQuery = true)
    void  updateUserEmail(String email,String login);
}
