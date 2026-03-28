package org.example._1_cheav_sarin_pp_spring_homework003.Model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendeeRequest {
    private Integer attendeeID;
    private  String attendeeName;
    private    String  email;
}
