package org.example._1_cheav_sarin_pp_spring_homework003.repository;

import org.apache.ibatis.annotations.*;
import org.example._1_cheav_sarin_pp_spring_homework003.Model.enity.Attendee;
import org.example._1_cheav_sarin_pp_spring_homework003.Model.request.AttendeeRequest;
import java.util.List;
@Mapper
public interface AttendeeRepository {
    @Results(id = "attendeeMapper", value = {
            @Result(property = "attendeeId", column = "attendee_id"),
            @Result(property = "attendeeName", column = "attendee_name")
    })
    @Select("""
        SELECT * FROM attendees
        LIMIT #{size} OFFSET (#{page} - 1) * #{size}
    """)
    List<Attendee> findAllAttendeeWithPagination(Integer page,
                                                 Integer size);
    @Select("""
        SELECT * FROM attendees
        WHERE attendee_id = #{attendeeId}
        """)
    @Results({
            @Result(property = "attendeeId", column = "attendee_id"),
            @Result(property = "attendeeName", column = "attendee_name"),
            @Result(property = "email", column = "email")
    })
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
   WHERE attendee_id = #{id}
""")



    void deleteAttendeeById(Integer attendeeId);
}

