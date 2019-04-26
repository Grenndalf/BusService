package pl.spring.finalProject.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.spring.finalProject.domain.entities.Railways;

public interface RailwaysRepository extends JpaRepository<Railways, Long> {

    Railways findById(long id);

    Railways findBycity(String city);

    Railways findByCityAndRailwayAddress(String city, String railwayAddress);

}
