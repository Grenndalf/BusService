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


    @Query("select b from Bus b where b.travelDate = :date")
    List<Bus> findByDateCustomQuery(@Param ( "date" ) LocalDate localDate);

    @Query(value = "select * FROM bus where start_Point_id=:sp", nativeQuery = true)
    List<Bus> findbysomespid(long sp);

    @Query(value = "select * FROM bus where end_Point_id=?1", nativeQuery = true)
    List<Bus> findbysomeepid(long sp);

    @Query(value = "select * FROM bus where start_point_id=:sp and end_point_id=:ep and travel_date=:dateInput", nativeQuery = true)
    List<Bus> findCustomQuery2(long sp, long ep, String dateInput);


}
