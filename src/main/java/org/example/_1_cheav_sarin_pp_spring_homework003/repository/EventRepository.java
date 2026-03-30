package org.example._1_cheav_sarin_pp_spring_homework003.repository;

import org.apache.ibatis.annotations.*;
import org.example._1_cheav_sarin_pp_spring_homework003.Model.enity.Event;
import org.example._1_cheav_sarin_pp_spring_homework003.Model.request.EventRequest;

import java.util.List;

@Mapper
public interface EventRepository {

    @Results(id = "eventMapper", value = {
            @Result(property = "eventId", column = "event_id"),
            @Result(property = "eventName", column = "event_name"),
            @Result(property = "eventDate", column = "event_date"),
            @Result(property = "venue", column = "venue_id",
                    one = @One(select = "org.example._1_cheav_sarin_pp_spring_homework003.repository.VenueRepository.getVenueById")),
            @Result(property = "attendees", column = "event_id",
                    many = @Many(select = "org.example._1_cheav_sarin_pp_spring_homework003.repository.AttendeeRepository.getAttendeesByEventId"))

    })
    @Select("""
                SELECT * FROM events
                LIMIT #{size} OFFSET (#{page} - 1) * #{size}
            """)
    List<Event> findAllEventWithPagination(@Param("page") Integer page,
                                           @Param("size") Integer size);
    @ResultMap("eventMapper")
    @Select("""
                SELECT * FROM events
                WHERE event_id = #{eventId}
            """)
    Event getEventById(Integer eventId);

    @Insert("""
                INSERT INTO event_attendee (event_id, attendee_id)
                VALUES (#{eventId}, #{attendeeId})
            """)
    void insertEventAttendee(Integer eventId,
                             Integer attendeeId);

    @Update("""
                UPDATE events
                SET event_name = #{event.eventName},
                    event_date = #{event.eventDate},
                    venue_id = #{event.venueId}
                WHERE event_id = #{id}
            """)
    void updateEvent(@Param("id") Integer eventId,
                     @Param("event") EventRequest eventRequest);


    @Select("""
                INSERT INTO events (event_name, event_date, venue_id)
                    VALUES (#{eventName}, #{eventDate}, #{venueId}) RETURNING *;
            """)
    @ResultMap("eventMapper")
    Event saveEvent(EventRequest eventRequest);

    @Delete("""
    DELETE FROM events
   WHERE event_id = #{id}
""")
    void deleteEventById(Integer eventId);

    @Delete("""
    DELETE FROM event_attendee
   WHERE event_id = #{id}
""")
    void deleteAttendeeById(Integer eventId);
}
