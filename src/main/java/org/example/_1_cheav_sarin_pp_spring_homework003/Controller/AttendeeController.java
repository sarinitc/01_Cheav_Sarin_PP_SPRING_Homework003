package org.example._1_cheav_sarin_pp_spring_homework003.Controller;

import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.example._1_cheav_sarin_pp_spring_homework003.Model.enity.Attendee;
import org.example._1_cheav_sarin_pp_spring_homework003.Model.request.AttendeeRequest;
import org.example._1_cheav_sarin_pp_spring_homework003.Model.response.ApiResponse;
import org.example._1_cheav_sarin_pp_spring_homework003.service.Impl.AttendeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/attendees")
@RequiredArgsConstructor
public class AttendeeController {
    private final AttendeeService attendeeService;
    @GetMapping
    public ResponseEntity<ApiResponse<List<Attendee>>> getAllAttendee(
            @RequestParam @Min(value = 1, message = "must be greater than 0") Integer page,
            @RequestParam @Min(value = 1, message = "must be greater than 0") Integer size) {
        ApiResponse<List<Attendee>> response = ApiResponse.<List<Attendee>>builder()
                .status("OK")
                .message("Retrieved attendees successfully")
                .payload(attendeeService.getAllAttendee(page, size))
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/{attendeesId}")
    public ResponseEntity<ApiResponse<Attendee>> getAttendeeById(@PathVariable(name = "attendeesId") Integer attendeeId) {
        Attendee attendee = attendeeService.getAttendeeById(attendeeId);
        if (attendee != null) {
            ApiResponse<Attendee> response = ApiResponse.<Attendee>builder()
                    .status("200")
                    .message("Retrieved attendee with  " + attendeeId + " Successfully")
                    .payload(attendee)
                    .timestamp(LocalDateTime.now())
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(response);

        }
        ApiResponse<Attendee> response = ApiResponse.<Attendee>builder()
                .status("404")
                .message("Retrieved attendee with  -" + attendeeId + " not found")
                .payload(null)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

    }
    @PostMapping
    public ResponseEntity<ApiResponse<Attendee>> createAttendee(@RequestBody AttendeeRequest attendeeRequest) {
        Attendee attendee = attendeeService.createAttendee(attendeeRequest);

        ApiResponse<Attendee> response = ApiResponse.<Attendee>builder()
                .status("201")
                .message("Attendee created successfully")
                .payload(attendee)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{attendeesId}")
    public ResponseEntity<ApiResponse<Attendee>> updateAttendee(
            @PathVariable("attendeesId") Integer attendeeId,
            @RequestBody AttendeeRequest attendeeRequest) {

        Attendee attendee = attendeeService.updateAttendee(attendeeId,attendeeRequest);

        ApiResponse<Attendee> response = ApiResponse.<Attendee>builder()
                .status("Created")
                .message("Attendee updated successfully")
                .payload(attendee)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @DeleteMapping("/{attendeesId}")
    public ResponseEntity<ApiResponse<String>> deleteAttendee(
            @PathVariable("attendeesId") Integer attendeeId) {

        Boolean isSuccess = attendeeService.deleteAttendeeById(attendeeId);
        String statusCode = isSuccess? "200": "404";
        String msg = isSuccess? "Deleted attendee with id "+ attendeeId  +"successfully":" Attendee not found";
        ApiResponse<String> response = ApiResponse.<String>builder()

                .status(statusCode)
                .message(msg)
//                .payload("Deleted ID: " + attendeeId)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}