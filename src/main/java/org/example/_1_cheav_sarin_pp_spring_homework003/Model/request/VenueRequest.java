package org.example._1_cheav_sarin_pp_spring_homework003.Model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example._1_cheav_sarin_pp_spring_homework003.Model.enity.Attendee;
import org.example._1_cheav_sarin_pp_spring_homework003.Model.enity.Venue;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VenueRequest {
    private  String  venueName;
    private   String location;
    }
