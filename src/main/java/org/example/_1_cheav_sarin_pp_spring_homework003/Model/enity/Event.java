package org.example._1_cheav_sarin_pp_spring_homework003.Model.enity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Event {
    private Integer eventId;
    private String eventName;
    private LocalDateTime eventDate;
    private Venue venue;

    private List<Attendee> attendees;
}
