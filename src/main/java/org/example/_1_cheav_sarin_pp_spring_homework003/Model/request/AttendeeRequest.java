package org.example._1_cheav_sarin_pp_spring_homework003.Model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example._1_cheav_sarin_pp_spring_homework003.Model.enity.Attendee;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendeeRequest {
    private  String attendeeName;
    private    String  email;
}
