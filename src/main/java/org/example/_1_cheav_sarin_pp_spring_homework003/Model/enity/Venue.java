package org.example._1_cheav_sarin_pp_spring_homework003.Model.enity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Venue {
    private  Integer venue_id;
    private  String  venue_name;
    private   String location;
}
