package org.example._1_cheav_sarin_pp_spring_homework003.Controller;

import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.example._1_cheav_sarin_pp_spring_homework003.Model.enity.Attendee;
import org.example._1_cheav_sarin_pp_spring_homework003.Model.enity.Event;
import org.example._1_cheav_sarin_pp_spring_homework003.Model.enity.EventResponse;
import org.example._1_cheav_sarin_pp_spring_homework003.Model.request.AttendeeRequest;
import org.example._1_cheav_sarin_pp_spring_homework003.Model.request.EventRequest;
import org.example._1_cheav_sarin_pp_spring_homework003.Model.response.ApiResponse;
import org.example._1_cheav_sarin_pp_spring_homework003.service.Impl.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
@RestController
@RequestMapping("api/v1/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Event>>> getAllEvent(
            @RequestParam @Min(value = 1, message = "must be greater than 0") Integer page,
            @RequestParam @Min(value = 1, message = "must be greater than 0") Integer size) {
        ApiResponse<List<Event>> response = ApiResponse.<List<Event>>builder()
                .status("OK")
                .message("Retrieved event successfully")
                .payload(eventService.getAllEvent(page, size))
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/{eventId}")
    public ResponseEntity<ApiResponse<Event>> getEventById(@PathVariable(name = "eventId") Integer eventId) {
        Event event = eventService.getEventById(eventId);
        if (event != null) {
            ApiResponse<Event> response = ApiResponse.<Event>builder()
                    .status("200")
                    .message("Retrieved event with  " + eventId + " Successfully")
                    .payload(event)
                    .timestamp(LocalDateTime.now())
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        ApiResponse<Event> response = ApiResponse.<Event>builder()
                .status("404")
                .message("Retrieved event with  - " + eventId + " not found")
                .payload(null)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    @PutMapping("/{eventId}")
    public ResponseEntity<ApiResponse<Event>> updateEvent(
            @PathVariable("eventId") Integer eventId,
            @RequestBody EventRequest eventRequest) {

        Event event = eventService.updateAttendee(eventId,eventRequest);

        ApiResponse<Event> response = ApiResponse.<Event>builder()
                .status("OK")
                .message("Event updated successfully")
                .payload(event)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PostMapping
    public ResponseEntity<ApiResponse<Event>> createEvent(@RequestBody EventRequest eventRequest) {
        Event event = eventService.createEvent(eventRequest);

        ApiResponse<Event> response = ApiResponse.<Event>builder()
                .status("201")
                .message("Event created successfully")
                .payload(event)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @DeleteMapping("/{eventId}")
    public ResponseEntity<ApiResponse<String>> deleteEvent(
            @PathVariable("eventId") Integer eventId) {

        Boolean isSuccess = eventService.deleteEventById(eventId);
        String statusCode = isSuccess? "200": "404";
        String msg = isSuccess? "Deleted event with id "+ eventId  +"successfully":" Event not found";
        ApiResponse<String> response = ApiResponse.<String>builder()

                .status(statusCode)
                .message(msg)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
