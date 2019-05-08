package pl.spring.finalProject.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.spring.finalProject.domain.entities.Bus;
import pl.spring.finalProject.domain.entities.Ticket;
import pl.spring.finalProject.domain.entities.Traveler;

import java.util.ArrayList;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {


    @Query ( value = "select seat_number from ticket where bus_id =?1 and ticket_owner IS NULL", nativeQuery = true )
    ArrayList<Integer> seats(long id);

    @Modifying
    @Query ( value = "Update Ticket t set t.traveler = :traveler where t.bus =:bus and t.seatNumber =:seatNumber" )
    void ticketUpdate(@Param ( "traveler" ) Traveler traveler, @Param ( "bus" ) Bus bus, @Param ( "seatNumber" ) Integer seatNumber);

    @Query ( value = "SELECT CASE WHEN count(t) = 0 THEN false ELSE true END FROM Ticket t WHERE t.seatNumber = ?1 and t.bus = ?2 and t.traveler IS NULL " )
    boolean isSeatAvailable(Integer seatnumber,Bus bus);
}