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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);

    @Override
    public List<Event> getAllEvent(Integer page, Integer size) {


            return eventRepository.findAllEventWithPagination(page, size);
        }

    @Override
    public Event getEventById(Integer eventId) {

        Event event = eventRepository.getEventById(eventId);

        logger.warn("Event: {}", event.toString());

        return event;
    }

    @Override
    public Event updateAttendee(Integer eventId, EventRequest eventRequest) {
        eventRepository.updateEvent(eventId, eventRequest);
        // clean up event attendee by event id
        eventRepository.deleteAttendeeById(eventId);
        // recreate event attendee
        if (eventRequest.getAttendeeId() != null) {
            for (Integer attendeeId : eventRequest.getAttendeeId()) {
                eventRepository.insertEventAttendee(eventId, attendeeId);
            }
        }

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

        logger.warn("Event Id: {}", event.getEventId());

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
            // delete event attendees
        eventRepository.deleteEventById(eventId);
        eventRepository.deleteEventById(eventId);
            return true;
        }
    }

