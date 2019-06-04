package pl.spring.finalProject.web.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.spring.finalProject.DTOs.AnnouncementsDTO;
import pl.spring.finalProject.Services.AnnouncementsService;
import pl.spring.finalProject.domain.entities.Announcements;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AnnouncementsController {

    private AnnouncementsService announcementsService;

    public AnnouncementsController(AnnouncementsService announcementsService) {
        this.announcementsService = announcementsService;
    }
    @RequestMapping ("/add-announcement")
    @ResponseBody
    public void addAnnouncement(@Valid @RequestBody AnnouncementsDTO announcementsDTO){
        announcementsService.saveAnnouncement(announcementsDTO);
    }

    @RequestMapping ("/getAllAnnoucements")
    @ResponseBody
    public List<Announcements> getAllAnnouncements(){
       return announcementsService.getAnnouncement();
    }
}
