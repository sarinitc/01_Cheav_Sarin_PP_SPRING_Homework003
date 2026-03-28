package org.example._1_cheav_sarin_pp_spring_homework003.Controller;

import lombok.RequiredArgsConstructor;
import org.example._1_cheav_sarin_pp_spring_homework003.Model.enity.Attendee;
import org.example._1_cheav_sarin_pp_spring_homework003.Model.response.ApiResponse;
import org.example._1_cheav_sarin_pp_spring_homework003.service.Impl.AttendeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/attendee")
@RequiredArgsConstructor
public class AttendeeController {
    private final AttendeeService attendeeService;
    @GetMapping
    public ResponseEntity<ApiResponse<List<Attendee>>> getAllAttendee(
            @RequestParam Integer page,
            @RequestParam Integer size) {

        ApiResponse<List<Attendee>> response = ApiResponse.<List<Attendee>>builder()
                .status("OK")
                .message("Retrieved attendees successfully")
                .payload(attendeeService.getAllAttendee(page, size))
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
