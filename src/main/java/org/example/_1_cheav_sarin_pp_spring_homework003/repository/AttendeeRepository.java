package org.example._1_cheav_sarin_pp_spring_homework003.repository;

import org.apache.ibatis.annotations.*;
import org.example._1_cheav_sarin_pp_spring_homework003.Model.enity.Attendee;
import org.example._1_cheav_sarin_pp_spring_homework003.Model.request.AttendeeRequest;

import java.util.List;

@Mapper
public interface AttendeeRepository {

    @Results(id = "attendeeMapper", value = {
            @Result(property = "attendeeId", column = "attendee_id"),
            @Result(property = "attendeeName", column = "attendee_name"),
            @Result(property = "email", column = "email")
    })
    @Select("""
            SELECT * FROM attendees
            LIMIT #{size} OFFSET (#{page} - 1) * #{size}
            """)
    List<Attendee> findAllAttendeeWithPagination(@Param("page") Integer page,
                                                 @Param("size") Integer size);

    @ResultMap("attendeeMapper")
    @Select("""
            SELECT * FROM attendees
            WHERE attendee_id = #{attendeeId}
            """)
    Attendee getAttendeeById(@Param("attendeeId") Integer attendeeId);

    @ResultMap("attendeeMapper")
    @Select("""
            INSERT INTO attendees (attendee_name, email)
            VALUES (#{attendeeName}, #{email})
            RETURNING *;
            """)
    Attendee saveAttendee(AttendeeRequest attendeeRequest);

    @Update("""
            UPDATE attendees
            SET attendee_name = #{req.attendeeName},
                email = #{req.email}
            WHERE attendee_id = #{id}
            """)
    int updateAttendee(@Param("id") Integer attendeeId,
                       @Param("req") AttendeeRequest attendeeRequest);

    @Delete("""
            DELETE FROM attendees
            WHERE attendee_id = #{attendeeId}
            """)
    void deleteAttendeeById(@Param("attendeeId") Integer attendeeId);

    @ResultMap("attendeeMapper")
    @Select("""
            SELECT a.attendee_id, a.attendee_name, a.email
            FROM attendees a
            INNER JOIN event_attendee ea ON a.attendee_id = ea.attendee_id
            WHERE ea.event_id = #{eventId}
            """)
    List<Attendee> getAttendeesByEventId(@Param("eventId") Integer eventId);
}