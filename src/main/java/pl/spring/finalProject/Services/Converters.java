package pl.spring.finalProject.Services;

import pl.spring.finalProject.DTOs.AnnouncementsDTO;
import pl.spring.finalProject.DTOs.BusDTO;
import pl.spring.finalProject.DTOs.RailwaysDTO;
import pl.spring.finalProject.DTOs.TravelerDTO;
import pl.spring.finalProject.domain.entities.Announcements;
import pl.spring.finalProject.domain.entities.Bus;
import pl.spring.finalProject.domain.entities.Railways;
import pl.spring.finalProject.domain.entities.Traveler;

public class Converters {

    public static Announcements convertAnnouncementDTO(AnnouncementsDTO announcementsDTO) {
        Announcements announcements = new Announcements();
        announcements.setTitle(announcementsDTO.getTitle());
        announcements.setContent(announcementsDTO.getContent());
        return announcements;
    }
    public static AnnouncementsDTO convertAnnouncement(Announcements announcements) {
        AnnouncementsDTO announcementsDTO = new AnnouncementsDTO();
        announcementsDTO.setTitle(announcements.getTitle());
        announcementsDTO.setContent(announcements.getContent());
        announcementsDTO.setCreatedAt(announcements.getCreatedAt());
        return announcementsDTO;
    }

    public static RailwaysDTO convertRailways(Railways railways) {
        RailwaysDTO railwaysDTO = new RailwaysDTO();
        railwaysDTO.setCity(railways.getCity());
        railwaysDTO.setRailwayAddress(railways.getRailwayAddress());
        return railwaysDTO;
    }

    public static BusDTO convertBuses(Bus bus) {
        BusDTO busDTO = new BusDTO();
        busDTO.setStartPoint(convertRailways(bus.getStartPoint()));
        busDTO.setEndPoint(convertRailways(bus.getEndPoint()));
        busDTO.setTravelDate(bus.getTravelDate());
        busDTO.setDepartureTime(bus.getDepartureTime());
        return busDTO;
    }

    public static Traveler convertTravelerDTO(TravelerDTO travelerDTO) {
        Traveler traveler = new Traveler();
        traveler.setLogin(travelerDTO.getLogin());
        traveler.setFirstName(travelerDTO.getFirstName());
        traveler.setLastName(travelerDTO.getLastName());
        traveler.setPassword(travelerDTO.getPassword());
        traveler.setEmail(travelerDTO.getEmail());
        return traveler;
    }

    public static TravelerDTO convertTraveler(Traveler traveler) {
        TravelerDTO travelerDTO = new TravelerDTO();
        travelerDTO.setEmail(traveler.getEmail());
        travelerDTO.setFirstName(travelerDTO.getFirstName());
        travelerDTO.setLastName(travelerDTO.getLastName());
        return travelerDTO;
    }
}
