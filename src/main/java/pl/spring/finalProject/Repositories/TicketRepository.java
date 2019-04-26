package pl.spring.finalProject.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.spring.finalProject.domain.entities.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {




}
