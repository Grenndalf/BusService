package pl.spring.finalProject.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.spring.finalProject.domain.entities.Bus;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {

    Bus findById(long id);

    @Query(value = "select * FROM bus where start_point_id=:sp and end_point_id=:ep and travel_date=:dateInput", nativeQuery = true)
    List<Bus> findConnections(long sp, long ep, String dateInput);


}
