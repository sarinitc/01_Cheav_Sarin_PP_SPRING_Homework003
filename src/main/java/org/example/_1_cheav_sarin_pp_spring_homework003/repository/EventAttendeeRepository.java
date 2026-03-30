package org.example._1_cheav_sarin_pp_spring_homework003.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EventAttendeeRepository {

    @Insert("""
            INSERT INTO event_attendee(attendee_id, event_id) VALUES (#{attendeeId}, #{eventId});
            """)
    int saveToEventAttendee(Integer attendeeId, Integer eventId);
}
