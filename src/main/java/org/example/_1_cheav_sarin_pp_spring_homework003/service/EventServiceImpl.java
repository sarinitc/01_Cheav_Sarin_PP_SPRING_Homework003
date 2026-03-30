package org.example._1_cheav_sarin_pp_spring_homework003.service;

import lombok.RequiredArgsConstructor;
import org.example._1_cheav_sarin_pp_spring_homework003.Model.enity.Attendee;
import org.example._1_cheav_sarin_pp_spring_homework003.Model.enity.Event;
import org.example._1_cheav_sarin_pp_spring_homework003.Model.enity.EventResponse;
import org.example._1_cheav_sarin_pp_spring_homework003.Model.request.EventRequest;
import org.example._1_cheav_sarin_pp_spring_homework003.exception.BadRequestException;
import org.example._1_cheav_sarin_pp_spring_homework003.repository.AttendeeRepository;
import org.example._1_cheav_sarin_pp_spring_homework003.repository.EventRepository;
import org.example._1_cheav_sarin_pp_spring_homework003.service.Impl.EventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    @Override
    public List<Event> getAllEvent(Integer page, Integer size) {

            if(page == null || size <= 0 ){
                throw new BadRequestException("Page must be grater than 0");
            }
            if  (size == null || size <=0){
                throw new BadRequestException("Size must be grater than 0");
            }
            return eventRepository.findAllEventWithPagination(page, size);
        }

    @Override
    public Event getEventById(Integer eventId) {
        return eventRepository.getEventById(eventId);
    }

    @Override
    public Event updateAttendee(Integer eventId, EventRequest eventRequest) {
        eventRepository.updateEvent(eventId, eventRequest);
        return eventRepository.getEventById(eventId);
    }

    @Override
    public Event createEvent(EventRequest eventRequest) {
        Event  event  = eventRepository.saveEvent(eventRequest);

        if (eventRequest.getAttendeeId() != null) {
            for (Integer attendeeId : eventRequest.getAttendeeId()) {
                eventRepository.insertEventAttendee(event.getEventId(), attendeeId);
            }
        }

        return eventRepository.getEventById(event.getEventId());
    }

    @Override
    public EventResponse getEventResponseById(Integer eventId) {
        return null;
    }

    @Override
    public Boolean deleteEventById(Integer eventId) {
            System.out.println("Delete event ID: " + eventId);
        Event event = eventRepository.getEventById(eventId);
            if (event == null){return false;}
        eventRepository.deleteEventById(eventId);
            return true;
        }
    }

