package org.example._1_cheav_sarin_pp_spring_homework003.service.Impl;

import org.example._1_cheav_sarin_pp_spring_homework003.Model.enity.Attendee;
import org.example._1_cheav_sarin_pp_spring_homework003.Model.request.AttendeeRequest;

import java.util.List;

public interface AttendeeService {

    List<Attendee> getAllAttendee(Integer page, Integer size);

    Attendee getAttendeeById(Integer attendeeId);

    Attendee createAttendee(AttendeeRequest attendeeRequest);
}
