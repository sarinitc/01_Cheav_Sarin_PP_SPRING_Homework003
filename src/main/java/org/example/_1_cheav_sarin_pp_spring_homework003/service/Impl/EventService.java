package org.example._1_cheav_sarin_pp_spring_homework003.service.Impl;

import jakarta.validation.constraints.Min;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.example._1_cheav_sarin_pp_spring_homework003.Model.enity.Attendee;
import org.example._1_cheav_sarin_pp_spring_homework003.Model.enity.Event;
import org.example._1_cheav_sarin_pp_spring_homework003.Model.enity.EventResponse;
import org.example._1_cheav_sarin_pp_spring_homework003.Model.request.EventRequest;

import java.util.List;

public interface EventService {


 List<Event> getAllEvent(Integer page, Integer size);

 Event getEventById(Integer eventId);

 Event updateAttendee(Integer eventId, EventRequest eventRequest);

 Event createEvent(EventRequest eventRequest);

 EventResponse getEventResponseById(Integer eventId);

 Boolean deleteEventById(Integer eventId);
}
