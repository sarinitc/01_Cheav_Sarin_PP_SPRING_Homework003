package org.example._1_cheav_sarin_pp_spring_homework003.Model.enity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventResponse {
    private String eventName;
    private LocalDate eventDate;
    private Integer venueId;
    private List<Integer> attendeeId;
}
