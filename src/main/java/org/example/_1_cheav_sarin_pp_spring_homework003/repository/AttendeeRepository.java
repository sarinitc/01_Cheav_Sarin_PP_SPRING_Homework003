package org.example._1_cheav_sarin_pp_spring_homework003.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.example._1_cheav_sarin_pp_spring_homework003.Model.enity.Attendee;

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
}