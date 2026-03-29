package org.example._1_cheav_sarin_pp_spring_homework003.repository;

import org.apache.ibatis.annotations.*;
import org.example._1_cheav_sarin_pp_spring_homework003.Model.enity.Attendee;
import org.example._1_cheav_sarin_pp_spring_homework003.Model.enity.Venue;
import org.example._1_cheav_sarin_pp_spring_homework003.Model.request.AttendeeRequest;
import org.example._1_cheav_sarin_pp_spring_homework003.Model.request.VenueRequest;

import java.util.List;

public interface VenueRepository {
    @Results(id = "venueMapper", value = {
            @Result(property = "venueId", column = "venue_id"),
            @Result(property = "venueName", column = "venue_name"),
            @Result(property = "location", column = "location")
    })
    @Select("""
        SELECT * FROM venues
        LIMIT #{size} OFFSET (#{page} - 1) * #{size}
    """)
    List<Venue> findAllVenueWithPagination(@Param("page") Integer page,
                                                 @Param("size") Integer size);
    @Select("""
        SELECT * FROM venues
        WHERE venue_id = #{venueId}
        """)
    @Results({
            @Result(property = "venueId", column = "venue_id"),
            @Result(property = "venueName", column = "venue_name"),
            @Result(property = "location", column = "location")
    })
    Venue getVenueById(@Param("venueId") Integer venueId);
    @ResultMap("venueMapper")
    @Select("""
        INSERT INTO venues (venue_name, location)
        VALUES (#{venueName}, #{location})
        RETURNING *;
        """)
    Venue saveVenue(VenueRequest venueRequest);
}
