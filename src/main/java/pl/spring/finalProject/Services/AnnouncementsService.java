package pl.spring.finalProject.Services;

import org.springframework.stereotype.Service;
import pl.spring.finalProject.DTOs.AnnouncementsDTO;

import java.util.List;

@Service
public interface AnnouncementsService {

    void saveAnnouncement(AnnouncementsDTO announcementsDTO);

    List<AnnouncementsDTO> getAnnouncement();
}
