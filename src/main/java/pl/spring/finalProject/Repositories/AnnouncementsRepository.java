package pl.spring.finalProject.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.spring.finalProject.domain.entities.Announcements;

import java.util.List;

@Repository
public interface AnnouncementsRepository extends JpaRepository<Announcements, Long> {

    List<Announcements> findFirst5ByOrderByIdDesc();
}
