package pl.spring.finalProject.Services.ServiceImplementations;

import org.springframework.stereotype.Service;
import pl.spring.finalProject.DTOs.AnnouncementsDTO;
import pl.spring.finalProject.Repositories.AnnouncementsRepository;
import pl.spring.finalProject.Services.AnnouncementsService;
import pl.spring.finalProject.Services.Converters;
import pl.spring.finalProject.domain.entities.Announcements;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnnouncementsServiceImpl implements AnnouncementsService {

    private AnnouncementsRepository announcementsRepository;

    public AnnouncementsServiceImpl(AnnouncementsRepository announcementsRepository) {
        this.announcementsRepository = announcementsRepository;
    }

    @Override
    public void saveAnnouncement(AnnouncementsDTO announcementsDTO) {
        announcementsRepository.save(Converters.convertAnnouncementDTO(announcementsDTO));
    }

    public List<AnnouncementsDTO> getAnnouncement() {
        return announcementsRepository.findFirst5ByOrderByIdDesc().stream()
                .sorted(Comparator.comparing(Announcements::getId).reversed()).map(Converters::convertAnnouncement).collect(Collectors.toList());
    }
}
