package org.example._1_cheav_sarin_pp_spring_homework003.Model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example._1_cheav_sarin_pp_spring_homework003.Model.enity.Attendee;
import org.example._1_cheav_sarin_pp_spring_homework003.Model.enity.Venue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {
    private String eventName;
    private LocalDate eventDate;
    private Integer venueId;
    private  List <Integer> attendeeId;

}
