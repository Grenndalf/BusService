package pl.spring.finalProject.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.spring.finalProject.DTOs.ReturnResultFromGetConnectionsMethodDTO;
import pl.spring.finalProject.domain.entities.Bus;
import pl.spring.finalProject.domain.entities.Railways;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {

    Bus findById(long id);

    @Query ( value = "select * FROM bus where start_point_id=:sp and end_point_id=:ep and travel_date=:dateInput", nativeQuery = true )
    List<Bus> findConnections(long sp, long ep, String dateInput);

    @Query ( name = "ConnectionFounderQuery", nativeQuery = true )
    List<ReturnResultFromGetConnectionsMethodDTO> findFullInfo(String spCity,
                                                               String spRailwayAddress,
                                                               String epCity,
                                                               String epRailwayAddress,
                                                               String travelDate
    );

//      check comment in busServiceImpl class
//    @Query ( value = "SELECT CASE WHEN count(b.id) = 0 THEN false ELSE true END FROM Bus b join Railways sp on b.startPoint=sp.id " +
//            "join Railways ep on b.endPoint=ep.id " +
//            "where sp.city =:spCity " +
//            "and sp.railwayAddress =:spRa " +
//            "and ep.city=:epCity " +
//            "and ep.railwayAddress =:epRa " +
//            "and b.travelDate=:tDate " +
//            "and b.departureTime=:deptTime" )
//    boolean isBusConnectionAlreadyExist(@Param ( "spCity" ) String spCity,
//                                        @Param ( "spRa" ) String spRa,
//                                        @Param ( "epCity" ) String epCity,
//                                        @Param ( "epRa" ) String epRa,
//                                        @Param ( "tDate" ) LocalDate tDate,
//                                        @Param ( "deptTime" ) LocalTime deptTime
//                                        );

    @Query ( value = "SELECT CASE WHEN count(b.id) = 0 THEN false ELSE true END FROM bus b join railways sp on b.start_point_id=sp.id join railways ep on b.end_point_id=ep.id where " +
            "sp.city=?1 " +
            "and sp.railway_address=?2 " +
            "and ep.city=?3 " +
            "and ep.railway_address=?4 " +
            "and b.travel_date=?5 " +
            "and b.departure_time=?6 ", nativeQuery = true )
    Integer isBusConnectionAlreadyExist2(String spCity, String spRailwayAddress, String epCity, String epRailwayAddress, String travelDate, LocalTime departureTime);

    @Query ( value = "select * from bus b where" +
            " b.start_point_id=?1" +
            " and b.end_point_id =?2" +
            " and b.travel_date=?3" +
            " and b.departure_time=?4", nativeQuery = true )
    Bus findBus(Railways startPoint, Railways endPoint, String travelDate, LocalTime departureTime);

    @Modifying
    @Query ( value = "INSERT into bus (max_number_of_seats_available,departure_time,travel_date,end_point_id,start_point_id) VALUES(?1,?2,?3,?4,?5)", nativeQuery = true )
    public void saveBus2(Integer mnosa, LocalTime deptTime, String travelDate, Railways ep, Railways sp);

}